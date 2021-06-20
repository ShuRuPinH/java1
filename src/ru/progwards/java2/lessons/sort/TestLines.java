package ru.progwards.java2.lessons.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestLines {
    public static void main(String[] args) {
        // String fileName ="C:\\temp_for_sort\\split1\\12_split1.txt";
        String fileName = "sort.txt";

        int count = 0;
        try {
            BufferedReader br = Files.newBufferedReader(Path.of(fileName));
            while (br.readLine() != null) {
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("lines = " + count);
    }

}
