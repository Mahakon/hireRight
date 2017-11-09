package project.parser.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUrlReader {
    public static String[] getUrls(String filename) {
        BufferedReader br = null;
        FileReader fr = null;
        List<String> lines = new ArrayList<String>();

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                lines.add(sCurrentLine);
            }

        } catch (IOException e) {
        System.out.println("I/O Error: " + e.getMessage());

        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException e) {
                System.out.println("I/O Error: " + e.getMessage());
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            lines.remove("");
        }
        String[] a = new String[lines.size()];
        a = lines.toArray(a);

        return a;
    }
}
