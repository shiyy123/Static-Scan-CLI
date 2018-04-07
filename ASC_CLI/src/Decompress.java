import java.io.*;
import java.util.zip.*;

public class Decompress {

    public static String zippedDir = "";


    /**
     * get the real type of the compressed file
     * @param file
     * @return Zipped File Type
     */
    public static FileType getFileType(File file){
        FileInputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            byte[] head = new byte[4];
            if (-1 == inputStream.read(head)) {
                return FileType.UNKNOWN;
            }
            int headHex = 0;
            for (byte b : head) {
                headHex <<= 8;
                headHex |= b;
            }
            switch (headHex) {
                case 0x504B0304:
                    return FileType.ZIP;
                case 0x776f7264:
                    return FileType.TAR;
                case -0x51:
                    return FileType._7Z;
                case 0x425a6839:
                    return FileType.BZ2;
                case -0x74f7f8:
                    return FileType.GZ;
                case 0x52617221:
                    return FileType.RAR;
                case -7665664:
                    return FileType.TAR_GZ;
                default:
                    return FileType.UNKNOWN;
            }
        } catch (FileNotFoundException e) {
            System.err.println("This is not a right compressed file");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Can not open the file");
            System.exit(1);
        } finally {
            try{
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                System.err.println("Do not close the file correctly");
                System.exit(1);
            }
        }
        return FileType.UNKNOWN;
    }

    /**
     * generate the directory
     * @param outputDir
     * @param subDir
     */
    public static void createDirectory(String outputDir, String subDir){
        File file = new File(outputDir);
        if(!(subDir == null || subDir.trim().equals(""))){
            file = new File(outputDir + File.separator + subDir);
        }
        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }

    /**
     * uncompressed the zipped file
     * @param storeFile the zip file
     * @param outPath the path of the unzipped file
     */
    private static String deCompressZip(File storeFile, String outPath){
        File file = storeFile;
        String temp = outPath;
        FileInputStream fis = null;
        ZipInputStream zins = null;
        String compressDir = null;
        //do decompress
        try {
            fis = new FileInputStream(file);
            zins = new ZipInputStream(fis);
            ZipEntry ze = null;
            byte[] ch = new byte[256];
            boolean isCompressedDir = true;
            while ((ze = zins.getNextEntry()) != null) {
                File zfile = new File(temp + "/" + ze.getName());
                File fpath = new File(zfile.getParentFile().getPath());
                if(isCompressedDir){
                    compressDir = zfile.getPath();
                    isCompressedDir = false;
                }
                if (ze.isDirectory()) {
                    if (!zfile.exists())
                        zfile.mkdirs();
                    zins.closeEntry();
                } else {
                    if (!fpath.exists())
                        fpath.mkdirs();
                    FileOutputStream fouts = new FileOutputStream(zfile);
                    int i;
                    while ((i = zins.read(ch)) != -1)
                        fouts.write(ch, 0, i);
                    zins.closeEntry();
                    fouts.close();
                }
            }
            fis.close();
            zins.close();
        } catch (Exception e) {
            e.printStackTrace();
//            file.delete();
            System.err.println("File" + file.toString() + "decompressed failed, reason" + e.toString());
            System.exit(1);
        }
        return compressDir;
    }

    /**
     *
     * @param fileType the real type of the compressed file, in case someone modify the end of the file name
     * @param filePath the path of the compressed file
     * @param targetPath the path to save the decompressed file
     * @param delete delete the compressed file or not
     * @return 0: normal, 1: unknown compressed file, 2: cannot process this kind of compressed file now
     */
    public static int doDecompress(FileType fileType, String filePath, String targetPath, boolean delete){
        Process process;
        int scanRes = 0;
        try {
            switch (fileType){
                case TAR_GZ:
                    process = Runtime.getRuntime().exec("tar -zxf " + filePath +" -C " + targetPath);
                    process.waitFor();
                    scanRes = 0;
                    break;
//                case GZ:
//                    break;
//                case TAR:
//                    break;
                case ZIP:
                    zippedDir = deCompressZip(new File(filePath), targetPath);
                    scanRes = 0;
                    break;
//                case _7Z:
//                    break;
//                case RAR:
//                    break;
//                case BZ2:
//                    break;
                case UNKNOWN:
                    System.err.println("No such decompressed file type");
                    scanRes = 1;
                    break;
                default:
                    System.err.println("Can not process the "+ fileType +" compressed file");
                    scanRes = 2;
                    break;
            }

        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("Do not get the execution");
            scanRes = 1;
        }catch (InterruptedException e) {
//            e.printStackTrace();
            System.err.println("Decompressed failed");
            scanRes = 1;
        }
        return scanRes;
    }
}
