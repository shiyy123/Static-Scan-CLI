import java.io.*;

public class DoPre {

    private static String formatVulName(String name){
        name = name.toLowerCase();
        StringBuilder res = new StringBuilder();
        for(char c : name.toCharArray()){
            if(c == '_'){
                res.append(' ');
            }else{
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args){
        File file = new File("/home/cary/Test/res.txt");
        FileReader fileReader;

        File file1 = new File("/home/cary/Test/res2.txt");
        FileWriter fileWriter;
        int count = 16;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            fileWriter = new FileWriter(file1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String s = "";
            while (s != null){
                s = bufferedReader.readLine();
                bufferedWriter.write("id = " + s + ";");
                bufferedWriter.newLine();

                s = bufferedReader.readLine();
                bufferedWriter.write("name = " + "\"" + s + "\"" + ";");
                bufferedWriter.newLine();

                s = bufferedReader.readLine();
                bufferedWriter.write("zh_name = " + "\"" + s + "\"" + ";");
                bufferedWriter.newLine();

                s = bufferedReader.readLine();
                bufferedWriter.write("solution = " + "\"" + s + "\"" + ";");
                bufferedWriter.newLine();

                s = bufferedReader.readLine();
                bufferedWriter.write("zh_solution = " + "\"" + s + "\"" + ";");
                bufferedWriter.newLine();

                //s = bufferedReader.readLine();
                bufferedWriter.write("desc = " + "\"" + s + "\"" + ";");
                bufferedWriter.newLine();

                //s = bufferedReader.readLine();
                bufferedWriter.write("zh_desc = " + "\"" + s + "\"" + ";");
                bufferedWriter.newLine();

                bufferedReader.readLine();
                bufferedWriter.write("addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);");
                bufferedWriter.newLine();
                bufferedWriter.write("\n");

            }
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
