package ru.progwards.java2.lessons.sort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Semaphore;

public class Merger {

    final static String PATH = "C://temp_for_sort/";

    BufferedReader[] brARR;

    BufferedWriter br;
    int size;
    int start;
    Integer buffer[];
    static String from;

    Path out;


    private int nullLineCNT;

    static int countTr = 0;
    static int allTr = 0;


    public Merger(int size, int start, int fileNumber, String from, String to, String finalFile) {
        this.size = size;
        this.start = start;
        this.from = from;
        // rafARR = new RandomAccessFile[size];
        brARR = new BufferedReader[size];
        buffer = new Integer[size];
        if (finalFile == null) out = Paths.get(PATH + to + "/" + fileNumber + "_" + to + ".txt");
        else out = Path.of(finalFile);

        if (!Files.isDirectory(Paths.get(PATH + to))) {
            try {
                Files.createDirectories(Paths.get(PATH + to));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    void merging() throws IOException {

        for (int i = 0; i < size; i++) {
            // rafARR [i] = new RandomAccessFile(from+"/"+(i+start)+"_"+from+".txt","rw");
            brARR[i] = Files.newBufferedReader(Path.of(PATH + from + "/" + (i + start) + "_" + from + ".txt"));
        }

        for (int i = 0; i < size; i++) {
            readLine(i);
            //    System.out.println("filling");

        }
        writeInit();
        while (true) {
            Integer[] rez = minFind();
            if (rez[0] != null) write(rez[0]);
            else break;
            nullLineCNT = 0;
            do {
                if (readLine(rez[1])) break;
                else nullLineCNT++;
            } while (nullLineCNT <= size);
        }

        //  System.out.println("stop time = " + System.currentTimeMillis());


    }

    void writeInit() {

        try {
            Files.deleteIfExists(out);

            Files.createFile(out);
            br = Files.newBufferedWriter(out, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(Integer rez) throws IOException {

        //Files.writeString(out, rez + "\n", StandardOpenOption.APPEND);
        br.write(rez + "\n");
        br.flush();
    }

    boolean readLine(int pos) throws IOException {


        //String temp= rafARR[pos].readLine();
        String temp = brARR[pos].readLine();

        if (temp == null) {
            buffer[pos] = null;
            //  System.out.println("READ LINE  ["+pos+"] = "+buffer[pos]);
            return false;
        } else {
            buffer[pos] = Integer.parseInt(temp);

            // System.out.println("READ LINE  ["+pos+"] = "+buffer[pos]);
        }
        return true;
    }

    Integer[] minFind() {
        int min = Integer.MAX_VALUE;
        Integer minPos[] = new Integer[2];
        int pos = 0;

        //  if (notEmpty==false) System.exit(0);
        for (Integer in : buffer) {
            //  System.out.println("  min loop   in ="+in);
            if (in == null) {

                pos++;
                continue;
            }
            if (in < min) {
                min = in;
                minPos[0] = min;

                minPos[1] = pos;
            }

            pos++;
        }


        //  System.out.println("minFind  ["+minPos[1]+"] = "+minPos[0]);
        return minPos;
    }

    public static void merge_all(int threads, int allowThreads, int threadSIZE, String from, String to, String finalFile) {

        //Semaphore semaphore = new Semaphore(allowThreads);

        //  System.out.println("start :" + System.currentTimeMillis());
        // 10_000x100 >> 1M
        countTr = 0;
        allTr = threads;

        for (int i = 0; i < threads; i++) {
            final int tmp = i;
            final int strt = (tmp * threadSIZE == 0 ? 1 : tmp * threadSIZE);


            Thread treadN = new Thread(new Runnable() {
                @Override
                public void run() {
                    Merger merg1 = new Merger(threadSIZE, strt, tmp + 1, from, to, finalFile);
                    //Merger merg1= new Merger(threadSIZE,strt ,tmp+1,"split1","merge1");
                    try {
                        // semaphore.acquire();
                        merg1.merging();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // semaphore.release();
                    countThreds();
                }
            });
            treadN.start();
        }











/*        System.out.println("start :"+System.currentTimeMillis());

        Thread tread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Merger merg3= new Merger(200,1,1,"merge1", "merge2");
                try {
                    merg3.merging();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

tread3.start();*/


    }

    static void countThreds() {
        if ((++countTr) == allTr) {
            delete(from);
            ExternalSort.process();
        }
    }

    static void delete(String from) {
        try {
            File dir = new File(PATH + from);
            File[] listFiles = dir.listFiles();
            for (File f : listFiles) {
                f.delete();
            }
            dir.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        delete("merge1");
    }
}
