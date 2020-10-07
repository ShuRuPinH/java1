package Project.trash;

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
    Path orign;
    Path result;

    List<String> edtList;
    List<String> patchList;
    Map<Integer, String> file = new HashMap<>();


    public Patch(String editFile, String patchFile) {
        this.editFile = editFile;
        this.patchFile = patchFile;
        try {
            this.edt = Paths.get(editFile);
            this.patch = Paths.get(patchFile);

            this.result = Paths.get("").resolve("NEW_" + edt.getFileName());

            edtList = Files.readAllLines(edt);
            patchList = Files.readAllLines(patch);
            this.orign = getPthOrig();
            Files.deleteIfExists(result);
            Files.createFile(result);
        } catch (Exception e) {
            System.out.println("Constructor ERROR");
            e.printStackTrace();
        }
    }

    void patcher() {
        int line;


        try {
            Iterator<String> pIt = patchList.iterator();
            while (pIt.hasNext()) {
                String lineStr = pIt.next();
                if (lineStr.startsWith("***")) continue;
                else {
                    String[] lineX = lineReader(lineStr);
                    line = Integer.parseInt(lineX[0]);

                    Object[] chFo = difFromOrig(line, 0).toArray();
                    System.out.println(line + " line   -" + Arrays.toString(chFo));

                    if (lineX[1].equals("SAM")) {
                        if (chFo[0].equals(0) || chFo.length == 0) {
                            file.put(line, edtGetter(line));
                        } else if (chFo[0].equals(-1)) {
                            file.put(line, edtGetter(line));
                        } else {
                            file.put(line, edtGetter(line));
                            file.put((Integer) chFo[0], lineX[2]);

                        }

                    }
                    if (lineX[1].equals("DEL")) {
                        if (chFo[0].equals(0)) {
                            System.out.println("PATCH cannt be applied, dowbled edition at LINE:\"" + edtGetter(line) + "\" & \"" + lineX[2] + "\"");
                            file.put(line, "PATCH cannt be applied, dowbled edition at LINE:\"" + edtGetter(line) + "\" & \"" + lineX[2] + "\"");
                            return;
                        } else if (chFo[0].equals(-1)) {
                            file.put(line, lineX[2]);
                        } else {
                            file.put((int) chFo[0], lineX[2]);
                        }
                    }
                    if (lineX[1].startsWith("MOV")) {
                        if (chFo[0].equals(0) || chFo.length == 0) {
                            System.out.println("PATCH cannt be applied, dowbled edition at LINE:\"" + edtGetter(line) + "\" & \"" + lineX[2] + "\"");
                            file.put(line, "PATCH cannt be applied, dowbled edition at LINE:\"" + edtGetter(line) + "\" & \"" + lineX[2] + "\"");
                            return;
                        } else if (chFo[0].equals(-1)) {
                            file.put(line, edtGetter(line));
                            file.put(movePos(lineX[1]), lineX[2]);
                        } else {
                            file.put((int) chFo[0], lineX[2]);
                        }
                    }
                    if (lineX[1].startsWith("NEW")) {
                        if (chFo.length == 0) {
                            Object[] chFo2 = difFromOrig(line, 1).toArray();
                            System.out.println(Arrays.toString(chFo2));
                            file.put(line, edtGetter(line));
                            file.put((int) chFo2[0], lineX[2]);
                        } else {
                            file.put(line, lineX[2]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR in patcher");
            e.printStackTrace();
        }
    }


    ///// Patch-File lineReader
    String[] lineReader(String lineStr) {
        String[] res = new String[3];
        String[] tmp = lineStr.split(";");
        Arrays.fill(res, "");
        int n = 0;
        for (String x : tmp) {
            res[n] = x;
            n++;
        }
        return res;
    }

    //// moveExtractor
    int movePos(String str) {
        String temp = str.substring(4, str.length() - 1);
        String[] mvs = temp.split(",");
        return Integer.parseInt(mvs[0]);
    }


    ///// PATH ORIGINAL FILE from PATCH.file
    Path getPthOrig() {
        Iterator temp = patchList.iterator();
        temp.next();
        String str = ((String) temp.next()).substring(9);
        Path res = Paths.get(str);
        return res.getFileName();
    }


    String edtGetter(int strNum) throws Exception {
        String str = "";
        Iterator<String> iteRead = edtList.iterator();
        for (int i = 1; i <= strNum; i++) {
            str = iteRead.next();
        }
        return str;
    }

    ////// DIFFS EDITED from ORIGNAL
    List<Integer> difFromOrig(int num, int OnE) {
        int rOnE;
        Diff diff = new Diff(orign.toString(), edt.toString(), true);
        ArrayList<Integer[]> tempDiff = diff.diffStrs();
        if (OnE == 0) rOnE = 1;
        else rOnE = 0;
        ArrayList temp = new ArrayList();

        for (Integer[] x : tempDiff) {

            if (x[OnE] == num) temp.add(x[rOnE]);
        }
        return temp;
    }


    //////////////////////
    public static void main(String[] args) {
        Patch test = new Patch("file1e.txt", "file1.txt2020-10-01.patch");

        test.patcher();

        System.out.println(test.file);

    }
}
