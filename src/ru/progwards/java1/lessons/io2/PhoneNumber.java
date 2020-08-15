package ru.progwards.java1.lessons.io2;

public class PhoneNumber {

    public static String format(String phone){
         StringBuilder tel= new StringBuilder();
            try{
            for(char ch: phone.toCharArray())
        if( Character.isDigit(ch)){
            tel.append(ch);
        }
          if (tel.charAt(0) == '8'){
            tel.replace(0,0,"7");
        }
        tel.insert(0,'+');
        tel.insert(2,'(');
        tel.insert(6,')');
        tel.insert(10,'-');


        } catch (Exception e) {
            System.out.println("Error, \"null\" or wrong tel:");
        }
        return tel.toString();
    }


/*
Задача 1. Класс PhoneNumber                                                                                 01234567890
Создать статический метод public static String format(String phone), который форматирует телефон под формат +7(999)111-2233, входящий формат может быть различным:
- 79991112233
- 8(999)111-22-33
- 8 999 111 22 33

формальное задание на форматирование:
- выделить все цифры, отбросить все разделители
- проверить что цифр в номере 11 или 10, в противном случае выбросить исключение
- убрать спереди 8
- добавить, если нужно +7
- отформатировать под выходной шаблон
 */
public static void main(String[] args) {
    System.out.println(format(null));
}}