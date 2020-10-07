package Project.actual;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Patch {
    String editFile;
    String patchFile;
    boolean shot;

    DateTimeFormatter dtf =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    Path edt;
    Path patch;

    Path result;

    List<String> edtList;
    List<String> patchList;
    List res = new ArrayList<>();

    public Patch(String editFile, String patchFile) {
        this.editFile = editFile;
        this.patchFile = patchFile;
        try {
            this.edt = Paths.get(editFile);
            this.patch = Paths.get(patchFile);

            this.result = Paths.get("").resolve("NEW_" + edt.getFileName());

            edtList = Files.readAllLines(edt);
            patchList = Files.readAllLines(patch);

            Files.deleteIfExists(result);
            Files.createFile(result);
        } catch (Exception e) {
            System.out.println("Constructor ERROR");
            e.printStackTrace();
        }
    }

    List patcher() {
        int line;

        int start[];
        int[] end;

        try {
            for (int i = 1; i <= edtList.size() - 2; i++) {
                line = i;
                for (int j = 1; j <= patchList.size() - 2; j++) {
                    if (edtTrio(i).equals(patchTrio(j))) {
                        //    ANCHOR   S T A *****************************************************
                        if (patchGetter(j - 1).startsWith("STA")) {
                            if (!res.contains(extract(edtTrio(i))))
                                res.add(extract(edtTrio(i))); //***************start*************
                            start = new int[]{i + 3, j + 3};
                            System.out.println(Arrays.toString(start));
                            do {
                                j++;
                            } while (!patchGetter(j).startsWith("END"));
                            System.out.println(j);
                            do {
                                i++;
                                if (i >= edtList.size()) {
                                    System.out.println("ANCHORS ERROR");
                                    return res;
                                }
                            } while (!edtTrio(i).equals(patchTrio(j + 1)));
                            end = new int[]{i - 1, j - 1};
                            System.out.println(Arrays.toString(end));
                            if (end[0] - start[0] + 1 > end[1] - start[1]) {
                                System.out.println(" ERROR : The same part have been edited. ADDED:\""
                                        + edtGetter(start[0] + (end[1] - start[1])) + "\" or more..");
                                res.add(" ERROR : The same part have been edited. ADDED:\""
                                        + edtGetter(start[0] + (end[1] - start[1])) + "\" or more..");
                                return res;
                            }
                    /*
                    нашли строки для изменений, удаляем, добавляем записываем
                    строки в наче, в конце    +       проверка
                     */
                            for (int k = start[1]; k <= end[1]; k++) {
                                ///// DEL case

                                if (patchGetter(k).startsWith("DEL")) {
                                    boolean isDel = false;
                                    String temp = patchGetter(k).substring(3);
                                    for (int l = start[0]; l <= end[0]; l++) {
                                        String edtString = edtGetter(l);
                                        //  System.out.println(edtString);
                                        if (!temp.equals(edtString)) ;
                                            //   res.add(edtString);//*************** edt lines*****************
                                        else isDel = true;
                                    }
                                    if (!isDel) {
                                        System.out.println(" ERROR : The same part have been edited. MISSING:" + temp + "\" or more..");
                                        res.add(" ERROR : The same part have been edited. MISSING:" + temp + "\" or more..");
                                        return res;
                                    }
                                }

                                ///// ADD case
                                if (patchGetter(k).startsWith("ADD")) {
                                    String temp = patchGetter(k).substring(3);
                                    res.add(temp); //***************ADD
                                }

                            }

                        }
                        //    ANCHOR   E N 1 *****************************************************
                        if (patchGetter(j - 1).startsWith("EN1")) {
                            for (int k = 1; k <= j - 2; k++) {
                                String temp = patchGetter(k);
                                if (temp.startsWith("AD1")) res.add(temp.substring(3));  ///////*********
                            }
                            if (!res.contains(extract(edtTrio(i))))
                                res.add((extract(edtTrio(i)))); //**************************

                        }

                        //    ANCHOR   S T e *****************************************************
                        if (patchGetter(j - 1).startsWith("STe")) {
                            if (!res.contains(edtTrio(i))) res.add(extract(edtTrio(i)));
                            for (int k = j + 3; k <= patchList.size(); k++) {
                                String temp = patchGetter(k);
                                if (temp.startsWith("ADe")) res.add(temp.substring(3));
                                if (temp.startsWith("DEe")) {  ///удраление
                                    boolean isDel = false;
                                    String tempp = patchGetter(k).substring(3);
                                    for (int l = i + 2; l <= edtList.size(); l++) {

                                        String edtString = edtGetter(l);
                                        //  System.out.println(edtString);
                                        if (tempp.equals(edtString)) isDel = true;
                                    }
                                    if (!isDel) {
                                        System.out.println(" ERROR : The same part have been edited. MISSING:" + temp);
                                        res.add(" ERROR : The same part have been edited. MISSING:" + temp);
                                        return res;
                                    }
                                    return res;
                                }
                            }

                        }
/*
нужно сделать проверку последних строк в едт и  2
 */
                    }
                }
                res.add(edtGetter(i)); //******************lines*****************


            }


        } catch (Exception e) {
            System.out.println("ERROR in patcher");
            e.printStackTrace();
        }
        return res;
    }

    ///////////////////////////Extractor ///////////////////////////////////
    static String extract(List<String> l) {
        String res = "";
        int t = 1;
        for (String s : l) {
            if (t < 3) res = res + s + "\n";
            else res = res + s;
            t++;
        }
        return res;
    }


    ////// G E T T E R S ///////////////////////////////////////////////////
    String patchGetter(int strNum) throws Exception {
        String str = "";
        Iterator<String> iteRead = patchList.iterator();
        for (int i = 1; i <= strNum; i++) {
            str = iteRead.next();
        }
        return str;
    }


    String edtGetter(int strNum) throws Exception {
        String str = "";
        Iterator<String> iteRead = edtList.iterator();
        for (int i = 1; i <= strNum; i++) {
            str = iteRead.next();
        }
        return str;
    }


    List<String> patchTrio(int line) {
        List<String> exm = new ArrayList(patchList);
        List<String> res = new ArrayList<>();
        Iterator<String> iter = exm.iterator();
        for (int i = 1; i < line; i++) {
            iter.next();
        }
        res.add(iter.next());
        res.add(iter.next());
        res.add(iter.next());
        return res;

    }


    List<String> edtTrio(int line) {
        List<String> exm = new ArrayList(edtList);
        List<String> res = new ArrayList<>();
        Iterator<String> iter = exm.iterator();
        for (int i = 1; i < line; i++) {
            iter.next();
        }
        res.add(iter.next());
        res.add(iter.next());
        res.add(iter.next());
        return res;

    }
    //////////W R I T T E R/////

    void write() {
        try {
            Files.write(result, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////
    public static void main(String[] args) {
        Patch test = new Patch("file1e.txt", "file1.txt2020-10-07.patch");
        test.patcher();
        test.write();
        for (Object x : test.res
        ) {
            System.out.println(x);

        }

    }
}
