package test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class Person {
    public String name;
    public Date birth;
    public double salary;

    Person(String name, Date birth, double salary) {
        this.name = name;
        this.birth = birth;
        this.salary = salary;
    }

    /*
    Реализуйте метод сигнатурой void printPersons(Person[] persons), который выводит на консоль содержимое массива persons столбиком, одна строка - один человек, причем, каждая строка имеет вид:
|Вася      |01/01/1970|200 000,00|

1. Разделитель значений - "|"
2. Порядок вывода значений: name, birth, salary
3. Для имени name, ширина 10 символов, прижим влево
4. Для дня рождения birth формат "дд/мм/гггг"
5. Для зарплаты salary ширина 10 точность 2, и задать разделитель тысяч, в русской раскладке
*/
    void printPersons(Person[] persons) {
        Locale locale = new Locale("ru", "RU");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        for (Person p : persons) {
            System.out.format(locale, "|%-10s|%2$td/%2$tm/%2$tY|%3$,10.2f|\n", p.name, p.birth, p.salary);
        }
    }

    public static void main(String[] args) {
        Locale locale = new Locale("ru", "RU");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);

        System.out.format(locale, "|%-10s|%2$td/%2$tm/%2$tY|%3$,10.2f|\n", "fHfdg", Date.from(Instant.EPOCH), 200000.1);


    }


}