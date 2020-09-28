package test;

import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class sets {

    class User {
        public Integer id;
        public String name;

        User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public TreeSet<User> createSet() {
            TreeSet<User> uSet = new TreeSet<>(new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return Integer.compare(o2.id, o1.id);
                }

            });
            return uSet;
        }
    }


    String swapWords(String sentance) {
        StringTokenizer tok = new StringTokenizer(sentance, " .,-!\n");

        int n = tok.countTokens();
        String[] strarr = new String[n];
        int r = 0b0;
        for (int i = 0; i < n; i++) {
            if (r == 0) {
                if (i + 1 == n) {
                    strarr[i] = tok.nextToken();
                    continue;
                }
                strarr[i + 1] = tok.nextToken();
            } else strarr[i - 1] = tok.nextToken();
            r = ~r;
        }
        String res = strarr[0];
        for (int i = 1; i < n; i++) {
            res = res + " " + strarr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        sets rrrr = new sets();

        //   0      1       2            3       4     5    6      7       8
        System.out.println(rrrr.swapWords("Убитых словом, добивают молчанием. jhjhjh  (c) Уильям 787878 Шекспир."));
        System.out.format("|%04d|%#x|%###,###.###|", 2, 15, 3.25);

    }
}