package ru.progwards.java2.lessons.sort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalSort {

    final static String PATH = "C://temp_for_sort/";
    private static int step = 0;
    static long start;

    static private int min = Integer.MIN_VALUE;
    static private int max = Integer.MIN_VALUE;

    static Path outPath;
    static Path inPath;

    static String inFile;
    static String outFile;


    static void sort(String inFileName, String outFileName) throws InterruptedException {
        inFile = inFileName;
        outFile = outFileName;
        outPath = Paths.get(outFileName);

        inPath = Path.of(inFileName);
        start = System.currentTimeMillis();


        process();


        //todo deleting temp files & dirs


    }

    static void process() { ///   method witn count of calls ? step by step

        switch (++step) {
            case 1:
                System.out.println("Start spliting");

                Split.spliting(inFile);
                break;
            case 2:
                System.out.println(" Spliting ends in :" + (System.currentTimeMillis() - start));
                System.out.println("Start Merge stage 1");
                Merger.merge_all(200, 50, 100, "split1", "merge1", null);

                break;
            case 3:
                System.out.println(" Merge 1st step ends in :" + (System.currentTimeMillis() - start));
                System.out.println("Start Merge stage 2");
                Merger.merge_all(1, 1, 200, "merge1", "", outFile);
                break;
            case 4:
                System.out.println("All time external sort = " + (System.currentTimeMillis() - start));
                new File(PATH).delete();
                break;

            default:
                ;

        }
    }

    public static void main(String[] args) {

        try {
            sort("data.txt", "sort.txt");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
