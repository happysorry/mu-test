import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;

public class get {

    public static void main(String[]args) throws Exception{
        getHTML();
    }



    public static String getHTML() throws Exception {
        String path = "http://192.168.99.110:666/~/mn-cse/mn-name/AE1/RFID_Container_for_stage1";
        // String path = "http://192.168.99.110:666/~/mn-cse/sub-833804069";
        // http://192.168.99.110:666/~/mn-cse/sub-833804069
        //String path = "http://192.168.99.110:777/~/mn-cse/mn-name/AE2/Robot_Arm_Status_Container";
        StringBuilder result = new StringBuilder();
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("X-M2M-Origin", "admin:admin");
        conn.setRequestProperty("Accept", "application/xml");
        conn.setRequestMethod("GET");
        // try (var reader = new BufferedReader(
        //             new InputStreamReader(conn.getInputStream()))) {
        //     for (String line; (line = reader.readLine()) != null; ) {
        //         result.append(line);
        //     }
        // }
        int satus = conn.getResponseCode();
        System.out.println(satus);
        System.out.println(result);
        return result.toString();
     }
}
