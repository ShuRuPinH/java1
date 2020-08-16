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
        String[] obscene = {"Java", "Oracle", "Sun", "Microsystems", "Go"};


        try {
            censorFile("file10.txt", obscene);
        } catch (CensorException e) {
            System.out.println(e.toString());
        }

    }
}