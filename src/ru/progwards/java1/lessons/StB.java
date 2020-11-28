package ru.progwards.java1.lessons;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class StB {
    Path pathIn = Paths.get("input.txt");
    Path pathOut = Paths.get("output.txt");
    List<String> tmp = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    boolean minus = false;
    int code = 0;
    String codeStr = "";

    void readWrite() throws Exception {
        tmp = Files.readAllLines(pathIn);
        codeStr = tmp.get(0);
        if (codeStr == null) return;
        if (codeStr.startsWith("-")) {
            temp.append(codeStr.substring(1));
            minus = true;

        } else {
            temp.append(codeStr);
        }

        code = Integer.parseInt(temp.reverse().toString());

        if (minus) code *= -1;
        tmp.clear();
        tmp.add(String.valueOf(code));

        Files.deleteIfExists(pathOut);
        Files.createFile(pathOut);
        Files.write(pathOut, tmp, StandardOpenOption.APPEND);

    }

    public static void main(String[] args) {
        StB stb = new StB();
        try {
            stb.readWrite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

