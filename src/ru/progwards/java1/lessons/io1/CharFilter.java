package ru.progwards.java1.lessons.io1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter){
        try {
            FileInputStream inStrm = new FileInputStream(inFileName);
            byte[] inBytes = inStrm.readAllBytes();

            String strOut="";

            for (int i = 0; i < inBytes.length; i++) {
                String temp=""+(char)inBytes[i];

                for (int j= 0; j<filter.length();j++){
                    if ((char) inBytes[i] == filter.charAt(j)){ temp="";
                    break;}
                }
                strOut=strOut+temp;
            }
            inStrm.close();
            FileWriter outStrm = new FileWriter(outFileName);
            outStrm.write(strOut);
            outStrm.close();

        } catch (Exception e) {

                System.out.println("err");
            }


        }

    public static void main(String[] args) {
        filterFile("file1.txt", "file2.txt", "yyr345t\n");
    }


    }


