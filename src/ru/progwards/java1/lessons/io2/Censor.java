package ru.progwards.java1.lessons.io2;

import java.io.*;
import java.util.Scanner;

public class Censor {
    private static class CensorException extends Throwable{
        public String fileName = "";
        public Exception e;
        public CensorException(String fileName, Exception e){

            this.fileName = fileName;
            this.e =e;
        }
        @Override
        public String getMessage() {
            return  (fileName+ ":"+e);
        }

    }

    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {



        try (RandomAccessFile raf = new RandomAccessFile(inoutFileName, "rw")) {
            long pos = 0;
            StringBuilder newLn = new StringBuilder();

            raf.seek(0);
            while (pos < raf.length()) {
                String temp = new String(raf.readLine().getBytes("ISO-8859-1"), "UTF-8");
                StringBuilder rightemp = new StringBuilder();

                System.out.println(temp);
                for (char ch : temp.toCharArray()) {
                    if (Character.isAlphabetic(ch)) {
                        rightemp.append(ch);

                    } else if (rightemp.length() != 0) {



                        for (int i = 0; i < obscene.length; i++) {


                            if (rightemp.toString().equals(obscene[i])) {
                                int j=rightemp.length();
                                rightemp.delete(0,j);
                                for (int d=1; d<=j;d++){
                                    rightemp.append('*');
                                }

                            }
                        }

                        newLn.append(rightemp);
                        newLn.append(ch);

                        rightemp.delete(0, rightemp.length());
                    } else newLn.append(ch);

                }
                System.out.println(newLn);
                pos++;


            }


        }  catch (Exception e) {
            throw new CensorException(inoutFileName,e);
        }


    }

    /*
    Задача 3. Класс Censor
Создать статический метод public static void censorFile(String inoutFileName, String[] obscene),
в котором прочитать файл inoutFileName и заменить слова, содержащиеся в String[] obscene на '*',
 соответствующие количеству символов в слове, изменения записать в исходный файл. В случае возникновения ошибки,
 выбросить свое собственное исключение CensorException в котором сохранить - строку,
 полученную у оригинального exception через метод getMessage() и имя файла, в котором возникла ошибка.
 В классе перекрыть метод toString(), вернув <имя файла>:<строка ошибки>. Класс CensorException разместить в классе Censor

Например файл содержит:
Java — строго типизированный объектно-ориентированный язык программирования,
разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).
obscene = {"Java", "Oracle", "Sun", "Microsystems"}

Должен выдать результат:
**** — строго типизированный объектно-ориентированный язык программирования, разработанный компанией *** ************
(в последующем приобретённой компанией ******).
     */
    public static void main(String[] args) {
        String[] obscene = {"Java", "Oracle", "Sun", "Microsystems"};


        try {
            censorFile("file1.txt", null);
        } catch (CensorException e) {
            System.out.println(e.toString());
        }

    }
}
