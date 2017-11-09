package project.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import project.parser.algorithms.AlgKMP;
import project.parser.reader.FileUrlReader;
import project.parser.reader.ReaderURL;

public class ParserHTML {
    private String[] words;
    private String[] urlAdress;
    private Set<String> commands;

    private ParserHTML(String[] args) {
        if (isFile(args[0])) {
            urlAdress = FileUrlReader.getUrls(args[0]);
        } else {
            urlAdress = new String[1];
            urlAdress[0] = args[0];
        }
        words = args[1].split(",");
        commands= new HashSet<>();
        for (int i = 2; i < args.length; i++) {
            commands.add(args[i]);
        }

        outResult();
    }

    public static ParserHTML newInstance(String[] args) {
        return new ParserHTML(args);
    }

    private boolean isFile(String arg) {
        FileReader fr = null;

        try {
            fr = new FileReader(arg);

        } catch (FileNotFoundException e) {
            return false;

        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println("I/O Error: " + e.getMessage());
                }
            }
        }
        return true;
    }

    private void outResult() {

        long lBegin = System.currentTimeMillis();
        for (int i = 0; i < urlAdress.length; i++) {
            long numChar = 0;
            System.out.println(urlAdress[i] + ":");
            long lBegin1 = System.currentTimeMillis();
            for (int j = 0; j < words.length; j++) {
                int totalNum = 0;
                boolean isAllWords = true;

                for (String line : ReaderURL.getLines(urlAdress[i])) {
                    if (commands.contains("-c")) {
                        numChar += line.length();
                    }
                    if (commands.contains("-w")) {
                        int n = AlgKMP.indexesOf(words[j].toCharArray(), line.toCharArray()).length;
                        totalNum += n;
                    }

                }
                if (commands.contains("-w")) {
                    System.out.println("\t" + words[j] + ": " + totalNum);
                }

            };
            long lEnd1 = System.currentTimeMillis();
            if (commands.contains("-c")) {
                System.out.println("\tTotal number of characters:" + numChar / words.length);
            }
            if (commands.contains("-v")) {
                System.out.println("\tTime: " + (lEnd1 - lBegin1));
            }
        }
        long lEnd = System.currentTimeMillis();
        if (commands.contains("-v")) {
            System.out.println("\tTotalTime: " + (lEnd - lBegin));
        }
    }
}
