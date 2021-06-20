package ru.progwards.java2.lessons.sort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Split {


    public static void spliting(String file) {
        int BUFFER_SIZE = 10_000;
        // int BUFFER_SIZE =10;
        final String PATH = "C://temp_for_sort/";
        String outDir = "split1";


        try {
            Files.createDirectories(Path.of(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Integer buffer[] = new Integer[BUFFER_SIZE];

        String lineTxt;
        int lineCount = 0;
        BufferedReader reader;

        //  try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((lineTxt = reader.readLine()) != null) {


                lineCount++;

                int pos = ((lineCount - 1) % BUFFER_SIZE);

                buffer[pos] = Integer.parseInt(lineTxt);
                if (pos == BUFFER_SIZE - 1) {

                    QSort.sortH(buffer);

                    String fileName = (lineCount / BUFFER_SIZE) + "_" + outDir + ".txt";

                    if (!Files.isDirectory(Paths.get(PATH + outDir))) {
                        try {

                            Files.createDirectory(Paths.get(PATH + outDir));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Path out = Paths.get(PATH + outDir + "/" + fileName);

                    Files.deleteIfExists(out);
                    Files.createFile(out);


                    List<String> writerr = new LinkedList();

                    for (Integer x : buffer) {
                        writerr.add(String.valueOf(x));

                        //  Files.writeString(out, x + "\n", StandardOpenOption.APPEND);
                    }
                    Files.write(out, writerr);


                }


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ExternalSort.process();
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        spliting("data.txt");
        System.out.println("Spliting time = " + (System.currentTimeMillis() - start));
    }

}
