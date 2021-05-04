import java.io.BufferedReader;

import java.io.DataOutputStream;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.MalformedURLException;

import java.net.URL;

import java.util.Random;



public class mu_test implements Runnable {

    static long val = 0;

    /**

     * calculate 1/lambda

     */

    public static long cal_send_time(double lambda) {



        double send_time = Math.log(1 - new Random().nextDouble()) / (-lambda);
        // double send_time = 1 / lambda;

        send_time *= 1e9;// change to nanosecond

        long s = (long) send_time;

        // System.out.println(s);

        return s;



    }



    public static void stage1() {

        try {

            val ++;

            String con = "";
            con = "false";

            if ((val % 2) == 1)

                con = "false";

            else

                con = "true";

            // String path = "http://192.168.99.110:666/~/mn-cse/mn-name/AE1/RFID_Container_for_stage2";
            // String path = "http://192.168.99.110:1111/test";
            // String path = "http://192.168.99.110:2222/test";
            // String path = "http://192.168.99.110:666/~/mn-cse/mn-name/AE1/Defective_Product_Container";
            String path = "http://192.168.99.110:777/~/mn-cse/mn-name/AE2/Robot_Arm_Status_Container";
            URL url = new URL(path);

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDoOutput(true);

            // http.setRequestProperty("Accept", "application/json");

            http.setRequestProperty("X-M2M-Origin", "admin:admin");

            http.setRequestProperty("Content-Type", "application/json;ty=4");

            try {

                http.setRequestMethod("POST");

                http.connect();

                DataOutputStream out = new DataOutputStream(http.getOutputStream());



                // String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><m2m:sgn xmlns:m2m=\"http://www.onem2m.org/xml/protocols\"><nev><rep rn=\"" + val +"\"><con>hello world</con></rep><rss>1</rss></nev><sud>false</sud><sur>/in-cse/in-name/MY_SENSOR/DATA/SUB_MY_SENSOR</sur></m2m:sgn>";
                // String request = "{\"m2m:cin\": {\"con\": \"" + con

                //         + "\", \"cnf\": \"application/xml\",\"lbl\":\"req\",\"rn\":\"" + val + "\"}}";
                // '{"m2m:cin": {"con": "EXAMPLE_VALUE", "cnf": "text/plain:0"}}'

                String request = "{\"m2m:cin\": {\"con\": \"" + con
                + "\", \"cnf\": \"application/xml\",\"lbl\":\"req\",\"rn\":\"" + val + "\"}}";
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

        } catch (IOException e) {



        }

    }



    static void print_single_time(double elapsed, String filename) {

        try {

            elapsed /= 1e6; // convert nanosecond into microsecond

            filename = filename + ".txt";

            FileWriter fw1 = new FileWriter(filename, true);

            fw1.write(elapsed + "\n");

            fw1.flush();

            fw1.close();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

    static void print_val() {

        try {

            String filename = "val.txt";

            FileWriter fw1 = new FileWriter(filename);

            fw1.write(val + "\n");

            fw1.flush();

            fw1.close();

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }



    static void read_val(){

        String filename = "val.txt";

        try {

            FileReader fr = new FileReader(filename);

            BufferedReader r = new BufferedReader(fr);

            String line = "";

            

                try {

                    while ((line = r.readLine()) != null) {

                        val = Long.parseLong(line);

                    }

                    // System.out.println(val);

                } catch (NumberFormatException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                } catch (IOException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                }

            }catch (FileNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        

    }



    static void send_req(double lambda, String filename, double simtime) {

        read_val();

        // long send_time = cal_send_time(lambda);

        double starttime = System.nanoTime();

        double endtime = System.nanoTime();
        
        while (true) {
            long send_time = cal_send_time(lambda);
            double tmp = System.nanoTime();

            stage1();

            double elapse = System.nanoTime() - tmp;

            print_single_time(elapse, filename);

            print_val();

            // get_use();

            long end = System.nanoTime() + send_time;

            while (System.nanoTime() < end) {

            }

            endtime = System.nanoTime();

            tmp = (endtime - starttime) / 1e9;

            if (tmp > simtime)
                break;

        }

    }



    public static void main(String[] args) {

        send_req(1, "50s", 60);



    }



    @Override

    public void run() {

        // TODO Auto-generated method stub

        send_req(600, "600s", 60);

        return;

    }

}

