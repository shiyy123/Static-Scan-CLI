import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class BatchTest{

    private static List<String> absolutePaths = new ArrayList<>();
    private static List<String> outPaths = new ArrayList<>();

    private static void singleScan(final List<String> threadAbsolutePaths, final List<String> threadOutPaths, final int id){
        new Thread(()->{
            System.out.println("Thread" + id);
            for(int i = 0; i < threadAbsolutePaths.size(); i++){
                String args[] = {"start", "-F", threadAbsolutePaths.get(i)};

                Cli.curWorkPath = threadOutPaths.get(i);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                System.out.println("Scan " + threadAbsolutePaths.get(i) + ", start: " + dateFormat.format(new Date()));
                Cli.main(args);
                System.out.println("Scan end: " + threadAbsolutePaths.get(i) + " " + dateFormat.format(new Date()));
            }
        }).start();
    }

    private static void readFile(){
        String path = "/home/cary/Project/android_source_crawler/src_crawler/items.json";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            JSONArray jsonArray = new JSONArray();
            while ((line = br.readLine())!=null){
                jsonArray.put(new JSONObject(line));
            }
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String absolutePath = "/home/cary/Test/zips/"
                        + jsonObject.getJSONArray("download_details").getJSONObject(0).get("path").toString();

                String outPath = absolutePath.substring(0, absolutePath.lastIndexOf('.'));
                File outFile = new File(outPath);
                outFile.mkdir();

//                absolutePaths.add(absolutePath);
//                outPaths.add(outPath);

                String args[] = {"start", "-F", absolutePath};

                Cli.curWorkPath = outPath;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                System.out.println("Scan " + outPath + ", start: " + dateFormat.format(new Date()));
                Cli.main(args);
                System.out.println("Scan end: " + outPath + ", " + dateFormat.format(new Date()));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFromSpecifiedJson(){
        List<String> paths = getPaths();
        for (String path : paths) {
            String outPath = "/home/cary/Test/zips/" + path;
            String absolutePath = outPath + ".zip";

            File outFile = new File(outPath);
            outFile.mkdir();

            String args[] = {"start", "-F", absolutePath};

            Cli.curWorkPath = outPath;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println("Scan " + outPath + ", start: " + dateFormat.format(new Date()));
            Cli.main(args);
            System.out.println("Scan end: " + outPath + ", " + dateFormat.format(new Date()));
        }
    }

    private static List<String> getPaths(){
        List<String> paths = new ArrayList<>();
        FileInputStream file;
        int count = 1;
        try {
            file = new FileInputStream("/home/cary/Test/Record/list.md");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String s = Integer.toString(count) + ". ";
                if(line.contains(s)){
                    paths.add(line.charAt(line.indexOf(" ") + 1) + "/" + line.substring(line.indexOf(" ") + 1));
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    public static void main(String[] args){
        readFromSpecifiedJson();
//        Vector<List<String>> vectorAbsolutePaths = new Vector<>();
//        Vector<List<String>> vectorOutPaths = new Vector<>();
//        int threadNum = 4;
//        for (int i = 0; i < threadNum; i++) {
//            vectorAbsolutePaths.add(new ArrayList<>());
//            vectorOutPaths.add(new ArrayList<>());
//        }
//
//        for(int i = 0; i < absolutePaths.size(); i+=threadNum){
//            for(int j = 0; j < threadNum; j++){
//                if(i*threadNum + j < absolutePaths.size()){
//                    vectorAbsolutePaths.get(j).add(absolutePaths.get(i*threadNum + j));
//                    vectorOutPaths.get(j).add(outPaths.get(i*threadNum + j));
//                }
//            }
//        }
//
//        for (int i = 0; i < threadNum; i++) {
//            singleScan(vectorAbsolutePaths.get(i), vectorOutPaths.get(i), i);
//        }
    }
}