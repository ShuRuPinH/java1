package ru.progwards.java1;


import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Plist {


    void readWrite() throws Exception {


        File income = new File("input.txt");
        Scanner incScan = new Scanner(income);
        FileWriter fileWr = new FileWriter("output.txt");
        int legt = Integer.parseInt(incScan.nextLine());

        System.out.println(legt);

        incScan.close();
        System.gc();
        for (int i = 1; i <= legt; i++) {
            boolean lost = true;
            Scanner incScan2 = new Scanner(income);
            incScan2.nextLine();
            while (incScan2.hasNext()) {

                if (incScan2.nextInt() == i) {
                    lost = false;
                    break;
                }
            }
            if (lost) {

                fileWr.write(i + " ");
            }
            incScan2.close();
            if (i % 4 == 0) System.gc();
        }
        fileWr.close();

    }


    public static void main(String[] args) {
        Plist pl = new Plist();
        try {
            pl.readWrite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
