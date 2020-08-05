package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.util.Scanner;

public class LineCount {

    public static int calcEmpty(String fileName) {
        int ii = 0;
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String strFromFile = scanner.nextLine();
                if (strFromFile.length() == 0) ii++;
            }
            reader.close();
        } catch (Exception e) {
            return -1;
        }
        return ii;
    }
    public static void main(String[] args) {
        LineCount lc = new LineCount();
        System.out.println(calcEmpty("file1.txt"));
    }

}

