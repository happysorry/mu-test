import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class avg {
    static void avg(String filename) throws FileNotFoundException {
        double sum = 0.0;
        double cnt = 0;
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                sum += Double.parseDouble(line);
                cnt++;
                // System.out.println(line);
            }
        } catch (IOException e) {

        }
        sum /= cnt;
        System.out.println(filename + " = " + sum);
    }

    public static void main(String[] args) throws FileNotFoundException {
        avg("1s.txt");
        avg("use1.txt");
    }
}
