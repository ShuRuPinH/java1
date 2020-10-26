package Project.actual;


import ru.progwards.java1.lessons.datetime.Profiler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

class Diff {

    String originalFile;
    String examFile;
    boolean shot;

    DateTimeFormatter dtf =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    Path org;
    Path exm;
    Path ptch;

    List<String> orgList;
    List<String> exmList;

    int Line;

    public Diff(String originalFile, String examFile, boolean shot) {
        this.originalFile = originalFile;
        this.examFile = examFile;
        this.shot = shot;

        try {
            this.org = Paths.get(originalFile);
            this.exm = Paths.get(examFile);
            this.ptch = Paths.get("").resolve(org.getFileName() + LocalDate.now().toString() + ".patch");
            if (shot != true) {
                Files.deleteIfExists(ptch);
                Files.createFile(ptch);
            }


            this.orgList = Files.readAllLines(org);
            this.exmList = Files.readAllLines(exm);
        } catch (Exception e) {
            System.out.println(" ERROR input params ");
            e.printStackTrace();
        } finally {

        }

    }

    /////////////////////// MAKE ////////////////////////////////////
    List complDiff() {
        List res = new ArrayList();
        boolean ii = false;
        Integer[] before = {0, 0};
        try {

            for (Integer[] ank : diffStrs()) {
                if (ank[0] == 1 && ank[1] == 1) ii = true;


                /// adding in start
                if (!ii) {
                    for (int i = 1; i < ank[0]; i++) {
                        res.add("DEL" + orignGetter(i) + "\n");
                    }
                    for (int i = 1; i < ank[1]; i++) {
                        res.add("AD1" + examplGetter(i) + "\n");
                    }
                    res.add("EN1" + extract(orgTrio(ank[0])));///**

                }
                /*
                не забыть про изменеия в начале и в конце и про якорь менее 3х,
                проверка изменных строк  index changed lines

                 */
                System.out.println(Arrays.toString(ank));
                if (before[0] + 1 != ank[0] && ii) {
                    res.add("\n" + "STA" + extract(orgTrio(before[0])) + "\n");
                    for (int i = before[0] + 3; i < ank[0]; i++) {
                        res.add("DEL" + orignGetter(i) + "\n");
                    }
                    for (int i = before[1] + 3; i < ank[1]; i++) {
                        res.add("ADD" + examplGetter(i) + "\n");
                    }
                    res.add("END" + extract(orgTrio(ank[0])));///***********************
                }
                ii = true;
                before = ank;
            }
            int lastExmPairTrio = diffStrs().get(diffStrs().size() - 1)[1];
            int lastOrgPairTrio = diffStrs().get(diffStrs().size() - 1)[0];
            System.out.println("astExmPairTrio=" + lastExmPairTrio);
            System.out.println("exmList.size()=" + exmList.size());
            if (lastExmPairTrio + 2 < exmList.size()) {
                res.add("\n" + "STe" + extract(exmTrio(lastExmPairTrio)));
                for (int i = lastExmPairTrio + 3; i <= exmList.size(); i++) {
                    res.add("\n" + "ADe" + examplGetter(i));
                }
            } else if (lastOrgPairTrio + 2 < orgList.size()) {
                ; ///////// тут нужно случай удаления строк якорь + дел
                res.add("\n" + "STe" + extract(orgTrio(lastOrgPairTrio)));
                for (int i = lastOrgPairTrio + 3; i <= orgList.size(); i++) {
                    res.add("\n" + "DEe" + orignGetter(i));
                }
            }

        } catch (Exception e) {
            System.out.println("complDiff   ERROR");
            e.printStackTrace();
        }
        return res;
    }

    //////////////////      FINDING PAIRS OF  TRIADS   ////////////////////////////////
    List<Integer[]> diffStrs() {
        List<Integer[]> trios = new ArrayList();
        List<String> lines = new ArrayList<>();
        Profiler.enterSection(" cheking ");          ///***********************************

        for (int i = 1; i <= orgList.size() - 2; i++) {
            for (int j = 1; j <= exmList.size() - 2; j++) {
                if (orgTrio(i).equals(exmTrio(j))) {
                    // System.out.println(" equals    i=" + i + "  j=" + j);
                    Integer[] temp = {i, j};
                    trios.add(temp);
                    break;
                }
            }
        }
        Profiler.exitSection(" cheking ");///********************************************

        return trios;
    }

    ///////////////////////////Extractor ///////////////////////////////////
    static String extract(List<String> l) {
        String res = "";
        for (String s : l) {
            res = res + "\n" + s;
        }
        return res;
    }


    ////////////////////////////// G E T T E R S  //////////////////////////////////////
    String orignGetter(int strNum) throws Exception {
        String str = "";
        Iterator<String> iteRead = orgList.iterator();
        for (int i = 1; i <= strNum; i++) {
            str = iteRead.next();
        }
        return str;
    }

    String examplGetter(int strNum) throws Exception {
        String str = "";
        Iterator<String> iteRead = exmList.iterator();
        for (int i = 1; i <= strNum; i++) {
            str = iteRead.next();
        }
        return str;
    }


    List<String> orgTrio(int line) {
        List<String> org = new ArrayList(orgList);
        List<String> res = new ArrayList<>();
        Iterator<String> iter = org.iterator();
        for (int i = 1; i < line; i++) {
            iter.next();
        }
        res.add(iter.next());
        res.add(iter.next());
        res.add(iter.next());
        return res;

    }

    List<String> exmTrio(int line) {
        List<String> exm = new ArrayList(exmList);
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
////////////////////// W R I T E R /////////////////////////////////

    void writer() {
        int ad = 0;
        int de = 0;

        try {
            Files.writeString(ptch, "*** PATCHFILE: " + ptch + " AT " + dtf.format(LocalDateTime.now()) + "\n"
                    + "*** FROM:" + org.toAbsolutePath() + "\n" + "*** AND:" + exm.toAbsolutePath() + "\n", StandardOpenOption.APPEND);
            Files.writeString(ptch, "***    CHANGES: " + de + "- deleted lines; " + ad + "- added lines.", StandardOpenOption.APPEND);
            for (Object x : complDiff()) {
                if (((String) x).startsWith("AD") || ((String) x).startsWith("\nAD")) ad++;
                if (((String) x).startsWith("DE") || ((String) x).startsWith("\nDE")) de++;
                Files.writeString(ptch, (String) x, StandardOpenOption.APPEND);
            }


        } catch (Exception e) {
            System.out.println(" WRITER ERROR");
            e.printStackTrace();
        }
    }

///////////////////////// M A I N ///////////////////////////////////////////////////

    public static void main(String[] args) {
        String path1 = "";
        String path2 = "";

        System.out.println("** Input 1st file (original) path:");
        try (Scanner in = new Scanner(System.in);) {
            if (in.hasNext()) {
                path1 = in.next();
            }

            System.out.println("** Input 2nd file (1st programer edition) path:");
            if (in.hasNext()) {
                path2 = in.next();
            }

        } catch (Exception e) {
            System.out.println("SCANER ERROR");
        }


        Diff test = new Diff(path1, path2, false);
        test.writer();


        System.out.println(Profiler.getStatisticInfo());

    }
}
