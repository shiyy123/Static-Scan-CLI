import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class generateECharts {
    private static String getColor(){
//        return new Color(
//                (new Double(Math.random() * 128)).intValue() + 128,
//                (new Double(Math.random() * 128)).intValue() + 128,
//                (new Double(Math.random() * 128)).intValue() + 128).toString();
        return "#da0d68";
    }

    private static String singleVul(Vul vul) throws JSONException {
        JSONObject singleVulJSONObject = new JSONObject();

        singleVulJSONObject.put("name", vul.getZh_name());

        return "{name: \'" + vul.getName() + "\'," + "value: 1,"+"itemStyle: { color: \'" + getColor() + "\'" + "}},";

    }

    public static void main(String[] args){
        File file = new File("/home/cary/Test/chart.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

//            bw.write("{name: \'" + "Android漏洞列表" + "\'," + "itemStyle: { color: \'"
//                    + getColor() + "\'" + "},children:[{");
            String javaS = "";
            String CppS = "";
            String OCS = "";
            String otherS = "";

            int count = 0;
            for(int i = 0; i < VulList.vulList.size(); i++){
                Vul vul = VulList.vulList.get(i);
                if(vul.getDesc().length() > 0){
//                    bw.write(singleVul(vul));
//                    System.out.println(vul.getName());
                    if(vul.getName().endsWith("CPP")){
                        System.out.println(vul.getZh_name());
                    }
                    count++;
                }
//                bw.write(singleVul(vul));
            }
            System.out.println(count);

//            bw.write("}}" );

//            bw.flush();
//            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
