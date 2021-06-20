package ru.progwards.java2.lessons.sort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class __ExternalSort {


    static private Integer[] buffer = new Integer[10_000];
    static private int linesInFile = 0;
    static private int countBuff = 0;
    static private int lastRepCount = 0;
    static private int lastRepsIn = 0;


    static private int min = Integer.MIN_VALUE;
    static private int max = Integer.MIN_VALUE;

    static Path outPath;
    static Path inPath;

    static void sort(String inFileName, String outFileName) {


        outPath = Paths.get(outFileName);

        process(inFileName);

    }

    static void process(String file) {

        long start = System.currentTimeMillis();
        int allLines = reading(file);
        int crossings = allLines % 10_000 > 0 ? (allLines / 10_000) + 1 : (allLines / 10_000);
        System.out.println("LINES = " + allLines + "   crossings = " + crossings + "     reading = " + (System.currentTimeMillis() - start));
        for (int i = 0; i < crossings - 1; i++) {
            reading(file);
        }
        System.out.println("ALL TIME = " + (System.currentTimeMillis() - start));
    }

    static int reading(String file) {
        String line = "";
        linesInFile = 0;
        lastRepCount = 0;
        countBuff = 0;
        Arrays.fill(buffer, null);

        BufferedReader reader;

        //    try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
        try {
            ;

            reader = new BufferedReader(new FileReader(
                    file));
            while ((line = reader.readLine()) != null) {
                linesInFile++;
                // System.out.println(line);
                int temp = Integer.parseInt(line);

                if (temp > min) {
                    buffAdd(temp);
                    // ADD in buff
                } else if (temp == min) {
                    lastRepCount++;

                }
            }
            if (countBuff < 9999) {
                buffer = Arrays.copyOfRange(buffer, 0, countBuff);

                System.out.println("RESIZE  size" + buffer.length);
            }
            if (lastRepCount - lastRepsIn > 0) {
                for (int i = 0; i < lastRepCount - lastRepsIn; i++) {
                    buffAdd(min);
                }
            }
            System.out.println(sortArr());
            write();

            System.out.println(min + "      " + max);
        } catch (Exception e) {
            //     if (line.equals("")) System.out.println("ОШИБКА : Исходный файл содержит пустую строку №" +linesInFile );
            e.printStackTrace();
        }

        min = max;

        return linesInFile;
    }

    public static void buffAdd(int temp) {
        // System.out.println("count : " + count+ "   temp ="  +temp);

        if (countBuff > 9999 && temp < max) {
            int preMax = Integer.MIN_VALUE;
            int[] maxCnt = {0, 0};

            for (int i = 0; i < 10_000; i++) {
                //  System.out.println("i : "+i+"      buffer[i]" + buffer[i]+ "  preMax ="  +preMax);
                if (buffer[i] > preMax) {
                    if (buffer[i] == max) {


                        maxCnt[0] = i;
                        maxCnt[1] = maxCnt[1] + 1;

                        // System.out.println("MAX  =" + max + " maxCnt[0] " + maxCnt[0] + "\tmaxCnt[1] " + maxCnt[1]);
                    } else {

                        preMax = buffer[i];
                    }
                }
            }
            buffer[maxCnt[0]] = temp;
            if (maxCnt[1] == 1) {
                //  System.out.println("max_old = " + max + "   max_new = " + preMax);
                if (temp > preMax) max = temp;
                else max = preMax;

            }


            // пропускать если  больше или равен максимальному
        } else if (countBuff <= 9999) {
            if (temp > max) max = temp;
            buffer[countBuff++] = temp;

        }
    }

    static void write() {
        try {
            if (!Files.exists(outPath)) Files.createFile(outPath);
            for (Integer dig : buffer) {
                Files.writeString(outPath, dig + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int sortArr() {
        QSort.sortH(buffer);
        lastRepsIn = 0;
        int i = 1;
        while (buffer[buffer.length - 1 - i] == buffer[buffer.length - 1]) {
            lastRepsIn = i++;
        }

        return buffer[buffer.length - 1];
    }
// todo незабыть добавить проверенные повторы последней поз
    // LastRepsIn to ZERO after check

    public static void main(String[] args) {
        sort("data.txt", "sort.txt");
        // test();

    }

///////////////   T E S T  ///////////////////////
///////////////   T E S T  ///////////////////////
///////////////   T E S T  ///////////////////////


    static void test() {
        Integer[] arr = createArr(20_000);
        Integer[] arr2 = new Integer[20_000];
        System.arraycopy(arr, 0, arr2, 0, 20_000);

        long start = System.currentTimeMillis();
        QSort.sortH(arr);
        System.out.println("Qsort = " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        checkArr(arr2);
        System.out.println("check = " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        readTest("data.txt");
        System.out.println("reading  = " + (System.currentTimeMillis() - start));


    }


    static int checkArr(Integer[] arr) {
        int tmp = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > tmp) tmp = arr[i];
        }

        return tmp;
    }

    static int readTest(String file) {
        List<String> listLine = new ArrayList<>();

        String line;
        Integer min = Integer.MAX_VALUE;
        int cnt = 0;

        try {
            listLine = Files.readAllLines(Path.of(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String x : listLine) {
            int temp = Integer.parseInt(x);
            min = temp < min ? temp : min;
            cnt++;
        }

/*    try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
        while ((line=raf.readLine())!=null){
         *//*   int temp =Integer.parseInt(line);
            min = temp<min ? temp :min;
            cnt++;*//*
        }


    }
    catch (Exception e){}*/
        System.out.println("TEST LINES /empty/ = " + cnt);
        return min;

    }

    static Integer[] createArr(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt();
        }

        return arr;

    }

}