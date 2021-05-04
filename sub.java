import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class sub {
    public static void main(String args[]){
        sub();
    }



    public static void sub(){
        // String path = "http://192.168.99.110:666/~/mn-cse/mn-name/AE1/Defective_Product_Container";
        String path = "http://192.168.99.110:666/~/mn-cse/mn-name/AE1/RFID_Container_for_stage2";
        URL url;
            try {
                url = new URL(path);
                HttpURLConnection http;
            try {
                http = (HttpURLConnection) url.openConnection();
                http.setDoOutput(true);
                // http.setRequestProperty("Accept", "application/json");
                http.setRequestProperty("X-M2M-Origin", "admin:admin");
                http.setRequestProperty("Content-Type", "application/xml;ty=23");
                http.setRequestMethod("POST");
                http.connect();
                DataOutputStream out = new DataOutputStream(http.getOutputStream());
                // String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><m2m:sgn xmlns:m2m=\"http://www.onem2m.org/xml/protocols\"><nev><rep rn=\"" + val +"\"><con>hello world</con></rep><rss>1</rss></nev><sud>false</sud><sur>/in-cse/in-name/MY_SENSOR/DATA/SUB_MY_SENSOR</sur></m2m:sgn>";
                // String request = "<m2m:sub xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"sub222\"><nu>http://192.168.99.110:2222/test</nu><nct>2</nct></m2m:sub>";
                String request = "<m2m:sub xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"sub\"><nu>http://192.168.99.110:1111/test</nu><nct>2</nct></m2m:sub>";
                // String request = "<m2m:sub xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" rn=\"sub\"><nu>http://192.168.72.27:1111/test</nu><nct>2</nct></m2m:sub>";
                out.write(request.toString().getBytes("UTF-8"));
                out.flush();
                out.close();
                int satus = http.getResponseCode();
                System.out.println(satus);
                // System.out.println(path);
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
