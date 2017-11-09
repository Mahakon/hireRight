package project.parser.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * gets url
 * returns html lines
 */
public class ReaderURL {
    public static List<String> getLines(String urlAdress) {
        BufferedReader br = null;
        List<String> lines = new ArrayList<>();

        try {
            URL url = new URL(urlAdress);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("I/O Error: " + e.getMessage());
                }
            }
            return lines;
        }

    }
}
