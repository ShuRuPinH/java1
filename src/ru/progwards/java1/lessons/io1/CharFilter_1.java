package ru.progwards.java1.lessons.io1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CharFilter_1  {
    public static void filterFile(String inFileName, String outFileName, String filter){

        FileInputStream inStrm = null;
        try {
            inStrm = new FileInputStream(inFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] inBytes = new byte[0];
        try {
            inBytes = inStrm.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String strOut="";

        for (int i = 0; i < inBytes.length; i++) {
            String temp=""+(char)inBytes[i];

            for (int j= 0; j<filter.length();j++){
                if ((char) inBytes[i] == filter.charAt(j)){ temp="";
                    break;}
            }
            strOut=strOut+temp;
        }
        try {
            inStrm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter outStrm = null;
        try {
            outStrm = new FileWriter(outFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStrm.write(strOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStrm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


