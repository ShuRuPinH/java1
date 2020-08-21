package test;


import java.io.*;
import java.util.*;

public class contest {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        long sum = 0;
        int i = 1;
        while (i <= 40) {

            try {
                sum += in.nextLong();
            } catch (Exception e) {
                break;
            }

        }

        out.println(sum);

        out.flush();
    }

}
