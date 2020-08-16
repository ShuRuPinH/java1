package ru.progwards.java1.lessons.io2;

import static java.lang.Character.*;

public class Translator {
    private String[] inLang;
    private String[] outLang;

    public Translator(String[] inLang, String[] outLang)  {
        if (inLang == null || outLang ==null){
            inLang = new String[] {"error null"};
            inLang = new String[]{"error null"};
            System.out.println("error null");
        }
        this.inLang = inLang;
        this.outLang = outLang;
    }
    public String translate(String sentence){
        StringBuilder word = new StringBuilder(2);
        boolean up =false;
                StringBuilder sent = new StringBuilder();

        for (char tmpch: sentence.toCharArray()){

            if (Character.isAlphabetic(tmpch)){
                word.append(tmpch);
                if (tmpch==sentence.toCharArray()[sentence.length()-1])sent.append(word);

            }
            else if (word.length()!=0) {
                if (isUpperCase(word.charAt(0))) { up=true;}
                    word.setCharAt(0,Character.toLowerCase(word.charAt(0)));
                    for (int i=0; i<inLang.length; i++){
                     //   System.out.println("i="+i+"   word="+word.toString()+"   in="+inLang[i]);
                     if (word.toString().equals(inLang[i])){
                         word.delete(0,word.length());
                         word.append(outLang[i]);

                         break;
                     }}
                        if (up) {

                            word.setCharAt(0,Character.toUpperCase(word.charAt(0)));
                            up=false;
                 }
                        sent.append(word);
                        sent.append(tmpch);
                        word.delete(0,word.length());

                }
            else {
                sent.append(tmpch);
            }}


        return sent.toString();
    }





    /*
Задача 2. Класс Translator
Создать класс Translator - переводчик, который будет переводить предложения с одного языка на другой

2.1 Конструктор Translator(String[] inLang, String[] outLang), где inLang и outLang - массивы слов на разных языках,
например русском и английском. Сохранить значения параметров в приватных свойствах класса

2.2  Метод public String translate(String sentence), в котором найти слова, содержащиеся в sentence и в inLang и
заменить их на соответствующие в outLang. Пунктуация должна быть соблюдена (скопирована из оригинальной строки).
При этом надо соблюсти заглавные буквы, если они были в оригинальном тексте.
В inLang и outLang все слова хранятся в нижнем регистре.

Например, фраза
"Hello World!" будет переведена как "Привет Мир!"
при наличии слов "hello", "world" в inLang и "привет", "мир" в outLang
 */
    public static void main(String[] args) {
        String[] outLang={"make", "love", "not", "war"};
        String[] inLang={"твори", "любовь", "не", "войну"};

        Translator trans= null;
        try {
            trans = new Translator(inLang,outLang);
        } catch (Exception exception) {
            System.out.println("error");
        }
        String str = "make love not war!";

        System.out.println(trans.translate(str));


    }


}