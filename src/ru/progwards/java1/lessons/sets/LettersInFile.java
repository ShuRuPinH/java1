package ru.progwards.java1.lessons.sets;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class LettersInFile {
    static Set<Character> words = new HashSet<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
            'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'ё', 'а', 'б', 'в', 'г', 'д', 'е',
            'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
            'ь', 'э', 'ю', 'я'));
    static Set<Character> miner = new TreeSet<>();
    static String result = "";

    /*
Реализовать класс, считывающий содержимое файла и возвращающего набор букв, которые встречались в этом файле.
Буквы, это латинские [A..Z[ и [a..z] и русские [А..Я] и [а..я], остальные символы надо игнорировать

3.1 Метод public static String process(String fileName) - вернуть все буквы, которые встретились в файле,
сконкатенированные в виде строки. Буквы должны быть упорядочены по алфавиту, типа “ADEF...”.
 Все возникающие исключения, по работе с потоками, пробросить выше.
 */
    public static String process(String fileName) throws Exception {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String str = new String(scanner.nextLine().getBytes(), "UTF-8");
                System.out.println(str);

                for (char ch : str.toCharArray()) {
                    miner.add(ch);
                }
            }


            miner.retainAll(words);
            for (char i : miner) {
                result += i;
            }


        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        return result;
    }


    public static void main(String[] args) {
        LettersInFile tt = new LettersInFile();
        try {
            System.out.println(process("file1.txt"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

  /*       ArrayList <String> sml= new ArrayList<>();
        for (char i: tt.words1){
            String t="'"+Character.toLowerCase(i)+"'";
            sml.add(t);
        }
        System.out.println(Arrays.toString(new ArrayList[]{sml}));*/


    }
}
