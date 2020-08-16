package ru.progwards.java1.lessons.io2;

import java.io.*;


public class Censor_1 {
    private static class CensorException extends Throwable {
        public String fileName;
        public Exception e;

        public CensorException(String fileName, Exception e) {

            this.fileName = fileName;
            this.e = e;
        }

        @Override
        public String getMessage() {
            return (fileName + ":" + e);
        }

    }

    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try (RandomAccessFile raf = new RandomAccessFile(inoutFileName, "rw")) {
            long pos;
            long way=0;


            while (way< raf.length()) {
                raf.seek(way);
                String temp = new String(raf.readLine().getBytes());
                pos=raf.getFilePointer();
                System.out.println(raf.getFilePointer());
                long tmp=way;
                way+=raf.getFilePointer();

                for (String s : obscene) {
                    pos = temp.indexOf(s);

                    if (pos >= 0) {
                        raf.seek(pos+tmp);
                        int j = s.length();
                        for (int d = 1; d <= j; d++) {
                            raf.writeByte(42);
                        }

                    }


                }
            }

        } catch (Exception e) {
            throw new CensorException(inoutFileName, e);
        }

    }

    /*
    ������ 3. ����� Censor
������� ����������� ����� public static void censorFile(String inoutFileName, String[] obscene),
� ������� ��������� ���� inoutFileName � �������� �����, ������������ � String[] obscene �� '*',
 ��������������� ���������� �������� � �����, ��������� �������� � �������� ����. � ������ ������������� ������,
 ��������� ���� ����������� ���������� CensorException � ������� ��������� - ������,
 ���������� � ������������� exception ����� ����� getMessage() � ��� �����, � ������� �������� ������.
 � ������ ��������� ����� toString(), ������ <��� �����>:<������ ������>. ����� CensorException ���������� � ������ Censor

�������� ���� ��������:
Java � ������ �������������� ��������-��������������� ���� ����������������,
������������� ��������� Sun Microsystems (� ����������� ������������ ��������� Oracle).
obscene = {"Java", "Oracle", "Sun", "Microsystems"}

������ ������ ���������:
**** � ������ �������������� ��������-��������������� ���� ����������������, ������������� ��������� *** ************
(� ����������� ������������ ��������� ******).
     */
    public static void main(String[] args) {
        String[] obscene = {"Java", "Oracle", "Sun", "Microsystems", "Go"};


        try {
            censorFile("file10.txt", obscene);
        } catch (CensorException e) {
            System.out.println(e.toString());
        }

    }
}