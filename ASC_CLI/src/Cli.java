import org.apache.commons.cli.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

public class Cli {

    private static String updateTime;
    public static String curWorkPath;
    private static String outputDir;

    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updateTime = dateFormat.format(new Date());
        curWorkPath = getCurWorkPath();
    }

    /**
     * convert the
     * @param s the file type in infer
     * @return the file type we use to do the json merge, then show in the report
     */
    private static String convertType(String s){
        switch (s.toLowerCase()) {
            case "cpp":
                return "C++";
            case "c":
                return "C";
            case "java":
                return "Java";
            case "m":
                return "ObjectC";
            default:
                return s;
        }
    }

    /**
     * Format the vul name : a_b
     * @param name different types of vul name
     * @return formatted vul name
     */
    private static String formatVulName(String name){
        name = name.toLowerCase();
        StringBuilder res = new StringBuilder();
        for(char c : name.toCharArray()){
            if(c == '_' ||  c == '-'){
                res.append(' ');
            }else{
                res.append(c);
            }
        }
        return res.toString();
    }

    /**
     * process the format json to match the vulnerability format we define
     * @param filePath the path of scan res
     */
    private static JSONArray processJSON(String filePath) {
        JSONArray inputJsonArray;
        JSONArray outputJsonArray = new JSONArray();
        JSONObject outputJsonObj;
//        VulList vulList = new VulList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String temp;
            StringBuilder initJsonStr = new StringBuilder();

            while((temp = br.readLine()) != null){
                initJsonStr.append(temp);
            }

            inputJsonArray = new JSONArray(new JSONTokener(initJsonStr.toString()));
            for(int i = 0; i < inputJsonArray.length(); i++){
                JSONObject inputJsonObj = inputJsonArray.getJSONObject(i);
                outputJsonObj = new JSONObject();

                outputJsonObj.put("targetTaskId", 1);

                String location = inputJsonObj.get("file").toString();
                String type = location.substring(location.lastIndexOf('.') + 1);
                // The vulName has the .c, .m, .java
                String vulName = inputJsonObj.get("bug_type_hum") + "," + convertType(type);

                /*
                  vulType == -1, do not exist that vul type
                  vulType >= 0, means vulType in the vul map<vulName(String), vulType(int)>
                  */
                int vulType = VulList.getVulType(formatVulName(vulName));
                String sol = "", desc = "";
                if(vulType >= 0){
                    sol = VulList.vulList.get(vulType).getSolution();
                    desc = VulList.vulList.get(vulType).getDesc();
                }else {
                    // Find new vulnerability
                    vulType = VulList.vulList.size();
                    VulList.vulList.add(new Vul(vulType, vulName, "", "", "", "", ""));
                }

                outputJsonObj.put("name", vulName);
                outputJsonObj.put("description", desc);
                outputJsonObj.put("solution", sol);
                outputJsonObj.put("vulType", vulType);
//                outputJsonObj.put("riskLevel", inputJsonObj.get("severity"));
                outputJsonObj.put("riskLevel", 2);
                outputJsonObj.put("source", "android_source");
                outputJsonObj.put("updateTime", updateTime);

                outputJsonObj.put("location", inputJsonObj.get("file").toString());
                outputJsonObj.put("detail", inputJsonObj.get("qualifier"));

                //outputJsonObj.put("vulReference", vulReferences);
                outputJsonObj.put("extra", "");

                outputJsonArray.put(outputJsonObj);
            }
        } catch (JSONException | IOException e) {
            //e.printStackTrace();
        }

        return outputJsonArray;
    }

    /**
     * Merge the scan result json with the same vulnerability type
     * suit the language of the system
     * @param inputJsonArray the json array from the first scan
     * @return the json array after merge
     */
    private static JSONArray mergeJSON(JSONArray inputJsonArray, String lang){
        Map<String, JSONObject> map = new HashMap<>();
        JSONArray res = new JSONArray();

        for(int i = 0; i < inputJsonArray.length(); i++){
            try {
                JSONObject jsonObject = inputJsonArray.getJSONObject(i);
                String name = jsonObject.get("name").toString();
                if(map.containsKey(name)){
                    JSONObject temp = map.get(name);
                    String totalLoc = temp.get("location").toString() + ";;;" + jsonObject.get("location").toString();

                    jsonObject.put("location", totalLoc);

                    String totalDetail = temp.get("detail").toString() + ";;;" + jsonObject.get("detail").toString();
                    jsonObject.put("detail", totalDetail);
                }
                map.put(name, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(HashMap.Entry<String, JSONObject> entry : map.entrySet()) {
            JSONObject value = entry.getValue();

            try {
                JSONObject vul = new JSONObject();
                vul.put("targetTaskId", value.get("targetTaskId"));
                int type = Integer.parseInt(value.get("vulType").toString());
                if(lang.equals("zh-CN")){
                    vul.put("name", VulList.vulList.get(type).getZh_name());
                    vul.put("description", VulList.vulList.get(type).getZh_desc());
                    vul.put("solution", VulList.vulList.get(type).getZh_solution());
                }else {
                    vul.put("name", value.get("name").toString());
                    vul.put("description", value.get("description").toString());
                    vul.put("solution", value.get("solution").toString());
                }
                vul.put("riskLevel", value.get("riskLevel"));

                vul.put("vulType", value.get("vulType"));

                vul.put("source", "android_source");
                vul.put("updateTime", updateTime);
                vul.put("extra", "");

                String[] locs = value.get("location").toString().split(";;;");
                String[] details = value.get("detail").toString().split(";;;");

                JSONArray vulReferences = new JSONArray();
                int vulReLen = locs.length < details.length ? locs.length : details.length;
                for (int j = 0; j < vulReLen; j++) {
                    JSONObject singleVulReference = new JSONObject();
                    singleVulReference.put("location", locs[j]);
                    singleVulReference.put("description", details[j]);
                    singleVulReference.put("solution", "");
                    vulReferences.put(singleVulReference);
                }
                vul.put("vulReferences", vulReferences);

                res.put(vul);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * get the pid of the current process
     * @return pid
     */
    private static String getPID(){
        String name = ManagementFactory.getRuntimeMXBean().getName();
        return name.split("@")[0];
    }

    /**
     * modify the build.gradle, thus it can download the gradle faster
     * @param scanDir the dir of the android source code
     */
    private static void modifyBuildGradle(String scanDir){
        File gradleFile = new File(scanDir + File.separator + "build.gradle");
        StringBuilder buf = new StringBuilder();
        BufferedReader br = null;

        // read and modify the build.gradle

        if(gradleFile.exists()){
            try {
                br = new BufferedReader(new FileReader(gradleFile));
                String line;
                while ((line = br.readLine())!=null){
                    if(line.contains("jcenter()")){
                        buf.append("maven{ url 'http://maven.aliyun.com/nexus/content/groups/public/'}");
                    }else {
                        buf.append(line);
                    }
                    buf.append(System.getProperty("line.separator"));
                }
            } catch (FileNotFoundException e) {
                System.err.println("cannot read build.gradle file");
//                e.printStackTrace();
            } catch (IOException e) {
//                e.printStackTrace();
            } finally {
                if(br != null){
                    try {
                        br.close();
                    } catch (IOException e) {
//                        e.printStackTrace();
                    }
                }
            }
        }

        // write the modified content to the build.gradle
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(scanDir + File.separator + "build.gradle"));
            bw.write(buf.toString());
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            if(bw!=null){
                try {
                    bw.close();
                }catch (IOException e){
//                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * get the checker's output json
     * @param scanDir the dir to scan
     */
    private static int doScan(String scanDir, boolean isDebuggable){
        Process process, process1;

        File sdkFile = new File(scanDir + File.separator + "local.properties");
        if(!sdkFile.exists()){
            try {
                sdkFile.createNewFile();
            } catch (IOException e) {
//                e.printStackTrace();
                System.err.println("Fail to create file");
                System.exit(1);
            }
        }
        try {
            FileWriter fw = new FileWriter(sdkFile, false);
            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write("sdk.dir=/opt/android-sdk-linux");
            bw.write("sdk.dir=/home/cary/Android/Sdk");
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("Fail to write the local.properties");
        }

        // modify build.gradle
//        modifyBuildGradle(scanDir);

        try {

            process = Runtime.getRuntime().exec("./gradlew clean", null, new File(scanDir));

            processMessage(process.getInputStream(), isDebuggable);
            processMessage(process.getErrorStream(), isDebuggable);

            process.waitFor();

            process1 = Runtime.getRuntime().exec("infer run -o " + outputDir + " -- ./gradlew build", null, new File(scanDir));

            processMessage(process1.getInputStream(), isDebuggable);
            processMessage(process1.getErrorStream(), isDebuggable);


            process1.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("The scan directory should be an Android app built with gradle");
            return -1;
        }
        return 0;
    }

    /**
     * in case the block of the stream, and the process will die
     * @param inputStream the stream of process
     */
    private static void processMessage(final InputStream inputStream, final boolean isDebuggable){
        new Thread(() -> {
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            File file = new File(curWorkPath + File.separator + "scanLogs");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file, true);
                String line;
                while ((line = br.readLine()) != null){
                    if(isDebuggable){
                        System.err.println(line);
                        fileOutputStream.write(line.getBytes("utf-8"));
                        fileOutputStream.write("\r\n".getBytes());
                    }else {
                        fileOutputStream.write(line.getBytes("utf-8"));
                        fileOutputStream.write("\r\n".getBytes());
                    }
                }
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e){
                System.err.println("print processMessage Error");
            }

        }).start();
    }

    /**
     * store the pid for stop instruction
     * @param filePath the path to store the pid
     */
    private static void storePID(String filePath){
        try {
            File pidFile = new File(filePath + File.separator + "PID.txt");
//            if(!pidFile.exists()){
//                pidFile.createNewFile();
//            }
            FileWriter fw = new FileWriter(pidFile);
            fw.write(getPID());
            fw.flush();
            fw.close();
        } catch (IOException e) {
//                        e.printStackTrace();
            System.err.println("Can not create PID file");
            System.exit(1);
        }
    }

    /**
     * get the pid store in the file PID.txt
     * @param curWorkPath current work path
     * @return current process pid
     */
    private static String getFilePID(String curWorkPath){
        File pidFile = new File(curWorkPath + File.separator + "PID.txt");
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(pidFile));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder res = new StringBuilder();
            while ((line = bufferedReader.readLine())!=null){
                res.append(line);
            }
            return res.toString();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.err.println("Can not find PID file");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Can not open PID file");
            System.exit(1);
        }
        return "";
    }

    /**
     * get the path where the process run
     * @return current work path
     */
    private static String getCurWorkPath(){
        StringBuilder curPath = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("pwd");
            process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String temp;

            while ((temp = br.readLine()) != null) {
                curPath.append(temp);
            }
        } catch (IOException e) {
            System.err.println("No such instruction");
            System.exit(1);
//                e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("This process stopped");
            System.exit(1);
//                e.printStackTrace();
        }
        return curPath.toString();
    }

    private static Options opts = new Options();

    static {
        opts.addOption("H", "Help", false, "The command help");
        opts.addOption("F", "File", true, "Input your Android file path to scan");
        opts.addOption("D", "Debuggable", false, "Open the debuggable mode");
        opts.addOption("O", "Output", true, "Specify the output dir");
    }

    /**
     * print the help info
     * @param opts the args after the instruction
     */
    private static void printHelp(Options opts){
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp("Show how to use Android source code checker.", opts);
    }

    /**
     * The entry function
     * @param args -H help; -F the scan file path
     */
    public static void main(String[] args) {

        CommandLineParser cmdParser = new PosixParser();
        CommandLine cl;
        boolean isDebuggable = false;

        try {
            cl = cmdParser.parse(opts, args);
        } catch (ParseException e) {
            System.err.println("Unrecognized option. Please refer to usage below");
            printHelp(opts);
            System.exit(1);
            return;
        }

        if(cl.hasOption("H")){
            printHelp(opts);
        }

        if(cl.hasOption("D")){
            isDebuggable = true;
        }

        outputDir = curWorkPath + File.separator + "infer-out";
        if(cl.hasOption("O")){
            outputDir = cl.getOptionValue("O");
        }
        File outFile = new File(outputDir);
        if(!outFile.exists()){
            if(cl.hasOption("O")){
                System.err.println("No such file to output the result");
                System.exit(1);
            }else {
                outFile.mkdir();
            }
        }

        if(args.length == 0){
            System.err.println("Do not have enough arguments");
            System.exit(1);
        }

        // start the scan process
        switch (args[0]) {
            case "start":
                if (cl.hasOption("F")) {
                    String filePath = cl.getOptionValue("F");
                    File scanDir = new File(filePath);

                    if (scanDir.exists()) {
                        // Assume if it is not a directory, then it is a compressed file
                        if (!scanDir.isDirectory()) {
                            FileType fileType = Decompress.getFileType(scanDir);
                            if(fileType == FileType.UNKNOWN){
                                System.err.println("Can not process this kind of file");
                                System.exit(1);
                            }
                            int scanRes = Decompress.doDecompress(fileType, filePath, curWorkPath, false);
                            if(scanRes == 1 || scanRes == 2){
                                System.exit(1);
                            }
                            String []paths = filePath.split(File.separator);

                            filePath = "";
                            if(paths.length > 0){
                                String lastPath = paths[paths.length - 1];
                                lastPath = lastPath.substring(0, lastPath.indexOf('.'));

                                filePath = curWorkPath + File.separator + lastPath;

                            }else {
                                System.err.println("It is not a true path");
                                System.exit(1);
                            }

                            if(fileType == FileType.ZIP){
                                filePath = Decompress.zippedDir;
                            }

                        }
                        // store PID of current scan in the work path

                        storePID(curWorkPath);

                        // change the mod of the gradlew file
                        Process process;
                        try {
                            process = Runtime.getRuntime().exec("chmod -R 777 " + filePath);
                            process.waitFor();
                            process = Runtime.getRuntime().exec("chmod u+x " + filePath + "/gradlew");
                            process.waitFor();
                        } catch (IOException | InterruptedException e) {
//                            e.printStackTrace();
                            System.err.println("Do not have a gradlew script");
                            System.exit(1);
                        }
                        int scanRes = doScan(filePath, isDebuggable);
                        // scanRes.0: normal; else: mistakes
                        if (scanRes == 0) {
                            String lang = Locale.getDefault().toLanguageTag();
//                            System.out.println(mergeJSON(processJSON(curWorkPath + "/infer-out/report.json"), lang));

                            System.out.println(mergeJSON(processJSON(outputDir + "/report.json"), lang));
                        } else {
                            System.exit(1);
                        }
                    } else {
                        System.err.println("The file path is not a directory");
                        System.exit(1);
                    }
                }
                break;
            case "stop":
                try {
                    Process process = Runtime.getRuntime().exec("kill " + getFilePID(curWorkPath));
                    process.waitFor();
                } catch (IOException e) {
                    System.err.println("No such instruction");
                    System.exit(1);
//                e.printStackTrace();
                } catch (InterruptedException e) {
                    System.err.println("This process stopped");
                    System.exit(1);
//                e.printStackTrace();
                }
                break;
            default:
                System.err.println("Please start the checker before input any instruction. Add start before instruction");
                System.exit(1);
        }
    }
}
