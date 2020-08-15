package ru.progwards.java1.lessons.io1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {
        FileInputStream inStrm=null;
        FileWriter outStrm=null;
        try {
            inStrm = new FileInputStream(inFileName);
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
            outStrm = new FileWriter(outFileName);
            outStrm.write(strOut);
            outStrm.close();

        } catch (Exception e) {

            throw e;
        }
        finally {
            if(inStrm!=null) {
                try {
                    inStrm.close();
                } catch (IOException e) {
                    throw e;
                }
            }
            if(outStrm!=null) {
                try {
                    outStrm.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }


    }

    public static void main(String[] args)  {
        try {filterFile("file1.txt", "file2.txt", "yyr345t\n");}
        catch (Exception e){
            return;
        }
    }


}
