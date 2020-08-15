package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class CheapChat {

    public void scanLines() {


        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                while (scanner.hasNextLine()) {
                    String tstr = scanner.nextLine();
                    if (tstr.contains("Привет")) {
                        System.out.println("Здравствуйте!");
                        break;
                    }
                    if (tstr.contains("как дела")) {
                        System.out.println("Хорошо");
                        break;
                    }
                    if (tstr.contains("/stop")) return;
                    else System.out.println(tstr);
                }
            }
        }


    }


    String invertWords(String sentence) {
        int i = 0;

        String[] arr = sentence.split(" ");
        StringBuilder inv = new StringBuilder();

        while (i < arr.length) {

            inv.append(arr[arr.length - 1 - i] + ".");
            i++;
        }
        inv.deleteCharAt(inv.length() - 1);
        return inv.toString();
    }

    /*
   Реализовать метод с сигнатурой public String setStars(String filename)
    который читает файл filename и меняет в нем каждый 10-йбайт на символ *,
    при этом конкатенируя оригинальный символ в строку ответа.

В случае ошибки выбросить исключение IOException со строкой сообщения:равной имени класса оригинального сообщения

Например,при содержимом файла:

0123456789012345678A012345678B01

новое содержимое должно быть

012345678*012345678*012345678*01

и метод должен вернуть "9AB"
    */
    public String setStars(String filename) throws IOException {
        StringBuilder newstr = new StringBuilder();
        StringBuilder str = new StringBuilder();
        try (RandomAccessFile ffile = new RandomAccessFile(filename, "rw")) {

            for (int i=0; i<ffile.length();i++){
                ffile.seek(i);
                char chr=(char)ffile.readByte();


                if ((i+11)%10==0 ){
                    newstr.append(chr);
                    ffile.seek(i);

                  ffile.write(0x2A);

                }

            }


        } catch (Exception e) {
            throw new IOException(e.getClass().getCanonicalName());
        }
        return newstr.toString();

    }


    public static void main(String[] args) {
        CheapChat chat = new CheapChat();
        System.out.println(chat.invertWords("губит людей - не пиво!"));

        try {
            System.out.println(chat.setStars("fil2e1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

