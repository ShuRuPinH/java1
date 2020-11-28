package ru.progwards.java1;


import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Ids {
    Path pathIn = Paths.get("input.txt");
    List<String> temp = new ArrayList<>();

    Set<Integer> digit = new TreeSet<>();
    Set<Integer> losts = new TreeSet<>();


    void readWrite() throws Exception {

        temp = Files.readAllLines(pathIn);
        Object[] arrIn = temp.toArray();
        int legt = Integer.parseInt((String) arrIn[0]);

        Scanner scan1 = new Scanner((String) arrIn[1]);

        for (int i = 1; i <= legt; i++) {
            digit.add(i);

            if (i % 7000 == 0) System.gc();
        }
        int count = 0;
        while (scan1.hasNext()) {
            losts.add(Integer.valueOf(scan1.next()));
            if (count % 7000 == 0) System.gc();
        }

        scan1.close();
        digit.removeAll(losts);

        System.gc();
        FileWriter fR = new FileWriter("output.txt");
        for (Integer integ : digit) {
            fR.write(integ + " ");
        }
        fR.close();
    }


    public static void main(String[] args) {
        Ids pl = new Ids();
        try {
            pl.readWrite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
