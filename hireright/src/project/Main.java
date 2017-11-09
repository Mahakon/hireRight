package project;

import project.parser.ParserHTML;

public class Main {

    public static void main(String[] args) {
        /*String[] a = new String[5];
        a[0] = "/home/maha/programming/hireright/input.txt";
        a[1] = "Google,wiki,записью";
        a[2] = "-v";
        a[3] = "-c";
        a[4] = "-w";*/
        ParserHTML.newInstance(args);
    }
}
