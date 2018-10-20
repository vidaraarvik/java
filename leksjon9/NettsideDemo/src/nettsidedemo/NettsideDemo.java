package nettsidedemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class NettsideDemo {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.exit(-1);
        }

        try {
            URL url = new URL("https://" + args[0]);
            URLConnection conn = url.openConnection();
            
            InputStream inn = conn.getInputStream();
            //InputStreamReader isr = new InputStreamReader(inn);
            
            Scanner leser = new Scanner(inn);
            
            while (leser.hasNextLine()) {
                System.out.println(leser.nextLine());
            }
            
            inn.close();

        } catch (MalformedURLException ex) {
            System.exit(-1);
        } catch (IOException ex) {
            System.exit(-1);
        }

    }

}
