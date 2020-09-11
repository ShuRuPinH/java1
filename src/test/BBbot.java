package test;


import org.telegram.telegrambots.ApiContextInitializer;
import ru.progwards.java1.testlesson.ProgwardsTelegramBot;

import java.util.Scanner;

public class BBbot extends ProgwardsTelegramBot {
    private final String menu = "Может хочешь отгадать загадку? Шутку? Пиццу?  ";

    @Override
    public String processMessage(String text) {
        checkTags(text);

        if (foundCount() == 1) {
            if (checkLastFound("Я готов!! Привет)"))
                return "Я готов!! Привет) .." + menu;
            if (checkLastFound("привет"))
                return "Привет ...\n Погода сегодня ничего;)\n " + menu;
            if (checkLastFound("конец"))
                return "Вот и поговорили.";
            if (checkLastFound("дурак"))
                return "Сам дурак!!";
            if (checkLastFound("пиццу"))
                return "нее.. тут не буфет...";
            if (checkLastFound("шутки"))
                return moreJoks() + "\n" + "Понравилась ???..  пиши \"еще ..\" для \"добавки \":)";
            if (checkLastFound("пример"))
                return "Сумма 5ти первых простых чисел равна?";
            if (checkLastFound("загадка"))
                return "Много лепесточков острых –\n" +
                        "Красных, жёлтых, белых, пёстрых.\n" +
                        "На меня ты погляди-ка,\n" +
                        "Называюсь я ...";
            if (checkLastFound("отгадка"))
                return "Верно!  " + menu;
            //  return "Я правильно понял?" + getLastFound() + " Есть что добавить?";
        }

        if (foundCount() > 1)
            return "Это про что?... Объясни...";
        return "Что ? Я не понял... попробуй сказать по другому... " + menu;
    }




    public static void main(String[] args) {


        ApiContextInitializer.init();

        BBbot bot = new BBbot();
        bot.username = "BB_username_bot";
        bot.token = "1269339215:AAHDKhTwyFKH-OH-EBmMw9Ot7rMMmhUYY_M";

        bot.addTags("привет", "привет, здрасьте, здравствуй, добр, день, вечер, утро, hi, hello");

        bot.addTags("дурак", "дурак, придурок, идиот, тупой");
        bot.addTags("конец", "конец, все, стоп, нет");

        bot.addTags("шутки", "шутк, добавк, еще");
        bot.addTags("загадка", "отгадат, загадк");
        bot.addTags("Загадывай..", "загадат, отгадай");
        bot.addTags("пиццу", "пицц");
        bot.addTags("отгадка", "гвоздик");
        bot.addTags("Я готов!! Привет)", "/start");


        bot.start();
        //bot.test();
    }

    void test(){
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            System.out.println(processMessage(input));
        } while (!input.equals("стоп"));
    }

    static String moreJoks() {
        String[] jokes = {"Почему при любом обращении в техподдержку надо сначала доказать, что ты не дебил?",
                "В детстве я ждал когда прогреется кинескоп, сейчас я жду, когда у телевизора загрузится операционка. В чем прогресс?",
                "Показал собаку ветеринару. Ветеринар сказал: \"Собака отличная, спасибо что показали. С вас 700 рублей!\"",
                "Оптимист изобрел колесо, а пессимист - запасное колесо.",
                "Дальтоники переходят дорогу только на нижний сигнал светофора.",
                "В Венеции, если вы переплываете улицу на красный свет, у вас на месяц отбирают плавки!",
                "В современном мире люди удивляются намного больше тому, что у тебя нет инстаграма, чем высшего образования.",
                "Три моих любимых настолки:завтрак,обед и ужин...",
                "Пельмень - это мучная раковина, таящая мясную жемчужину.",
                "Сделал на телефоне папку, в которую скидал приложения, которыми не пользуюсь никогда, но почему-то не удаляю. Назвал \"Балкон\"."};
        return jokes[(int) (Math.random() * 10)];
    }
}
