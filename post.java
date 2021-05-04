import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class post {

    public static void main(String[]args){
        sub_ae();
    }



    public static void sub_ae(){
        // String path = "http://192.168.99.110:666/~/mn-cse/mn-name/AE1/RFID_Container_for_stage0";
        String path = "http://192.168.99.110:777/~/mn-cse/mn-name/AE2/Robot_Arm_Status_Container";
        URL url;
        int val = (int) ((Math.random() * 89999) + 10000);
        String RFID = String.valueOf(val);
        String con = "";
        con = "false";
        // if ((val % 2) == 1)
        //     con = "false";
        // else
        //     con = "true";
        // path = path + "/sub";
            try {
                url = new URL(path);
                HttpURLConnection http;
            try {
                http = (HttpURLConnection) url.openConnection();
                http.setReadTimeout(10000);
                http.setDoOutput(true);
                // http.setRequestProperty("Accept", "application/json");
                http.setRequestProperty("X-M2M-Origin", "admin:admin");
                http.setRequestProperty("Content-Type", "application/json;ty=4");
                http.setRequestMethod("POST");
                http.connect();
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
                String request = "{\"m2m:cin\": {\"con\": \"" + con
                + "\", \"cnf\": \"application/xml\",\"lbl\":\"req\",\"rn\":\"" + RFID + "\"}}";
                out.write(request.toString().getBytes("UTF-8"));
                out.flush();
                out.close();
                int satus = http.getResponseCode();
                System.out.println("sub " + satus);
                System.out.println(val);
                // System.out.println(status);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    }
}
