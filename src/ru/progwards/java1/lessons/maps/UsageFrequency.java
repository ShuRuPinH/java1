package ru.progwards.java1.lessons.maps;

import java.io.File;

import java.util.*;

public class UsageFrequency {
    private ArrayList<String> fileSrgs = new ArrayList();

    static Set<Character> letrsDgs = new HashSet<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
            'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'ё', 'а', 'б', 'в', 'г', 'д', 'е',
            'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
            'ь', 'э', 'ю', 'я', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));


    public void processFile(String fileName) {
        File file;
        if (fileName != null) file = new File(fileName);
        else return;
        try (Scanner scn = new Scanner(file)) {
            while (scn.hasNextLine()) {
                fileSrgs.add(new String(scn.nextLine().getBytes(), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Character, Integer> getLetters() {
        Map<Character, Integer> map = new HashMap<>();
        for (String arString : fileSrgs) {
            for (Character ch : arString.toCharArray()) {
                if (letrsDgs.contains(ch)) {   // if(Character.isLetterOrDigit(ch)) - пропускает иероглифы
                    if (map.containsKey(ch)) {
                        map.put(ch, 1 + map.get(ch));
                    } else {
                        map.put(ch, 1);
                    }
                }
            }
        }
        return map;
    }

    public Map<String, Integer> getWords() {
        Map<String, Integer> map = new HashMap<>();
        for (String arString : fileSrgs) {
            try (Scanner scnWord = new Scanner(arString).useDelimiter(" ")) {
                while (scnWord.hasNext()) {
                    String tmp = scnWord.next();
                    if (!Character.isLetterOrDigit(tmp.charAt(0))) continue;

                    if (map.containsKey(tmp)) {
                        map.put(tmp, 1 + map.get(tmp));
                    } else {
                        map.put(tmp, 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        return map;
    }

    /*
    Реализовать класс, подсчитывающий частоту использования слов и букв в словах на основе текстов. Методы:

2.1 public void processFile(String fileName) - загрузить содержимое файла

2.2 public Map<Character, Integer> getLetters() - вернуть Map, который содержит все найденные буквы и цифры,
и количество раз, которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и др не учитывать.

2.3 public Map<String, Integer> getWords() - вернуть Map, который содержит все найденные слова и количество раз,
которое каждое слово встретилось. Знаки препинания, такие как “.,!? @” и др являются разделителями.

2.4 Протестировать на файле wiki.train.tokens (во вложении), для отладки можно использовать wiki.test.tokens
     */
    public static void main(String[] args) {
        UsageFrequency test = new UsageFrequency();
        test.processFile("wiki.train.tokens");
        System.out.println(test.getLetters());
        System.out.println(test.getWords());

    }

}
