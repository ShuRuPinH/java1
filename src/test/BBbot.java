package test;


import org.telegram.telegrambots.ApiContextInitializer;
import ru.progwards.java1.testlesson.ProgwardsTelegramBot;

import java.util.Scanner;

public class BBbot extends ProgwardsTelegramBot {
    private final String menu = "����� ������ �������� �������? �����? �����?  ";

    @Override
    public String processMessage(String text) {
        checkTags(text);

        if (foundCount() == 1) {
            if (checkLastFound("������"))
                return "������ ...\n ������ ������� ������;)\n "+menu;
            if (checkLastFound("�����"))
                return "��� � ����������.";
            if (checkLastFound("�����"))
                return "��� �����!!";
            if (checkLastFound("�����"))
                return "����������� ???..  ���� \"��� �����\" ��� \"������� \":)";
            if (checkLastFound("������"))
                return "����� 5�� ������ ������� ����� �����?";
            if (checkLastFound("�������"))
                return "����� ����������� ������ �\n" +
                        "�������, �����, �����, ������.\n" +
                        "�� ���� �� �������-��,\n" +
                        "��������� � ...";
            if (checkLastFound("�������"))
                return "�����!  " + menu;
          //  return "� ��������� �����?" + getLastFound() + " ���� ��� ��������?";
        }

        if (foundCount() > 1)
            return "��� ��� ���?... �������...";
        return "� �� �����... �������� ������� �� �������... "+menu;
    }




    public static void main(String[] args) {



    	ApiContextInitializer.init();

    BBbot bot = new BBbot();
    bot.username = "BB_username_bot";
    bot.token = "1269339215:AAHDKhTwyFKH-OH-EBmMw9Ot7rMMmhUYY_M";

        bot.addTags("������", "������, ��������, ����������, ����, ����, �����, ����, hi, hello");

        bot.addTags("�����", "�����, ��������, �����, �����");
        bot.addTags("�����", "�����, ���, ����, ���");

        bot.addTags("������", "�����, ������, ��������");
        bot.addTags("�������", "�������, ������");
        bot.addTags("���������..", "�������, �������");

        bot.addTags("�������", "�������");
        bot.addTags("��... 6 + 2 ��������", "8, �����");



    //bot.start();
    bot.test();
    }

    void test(){
        Scanner in = new Scanner(System.in);
        String input;
        do{
            input=in.nextLine();
            System.out.println(processMessage(input));
        }while (!input.equals("����"));
    }

    static String moreJoks(){
        String [] jokes= {"������ ��� ����� ��������� � ������������ ���� ������� ��������, ��� �� �� �����?","� ������� � ���� ����� ���������� ��������, ������ � ���, ����� � ���������� ���������� �����������. � ��� ��������?",
        "������� ������ ����������. ��������� ������: \"������ ��������, ������� ��� ��������. � ��� 700 ������!\"","�������� ������� ������, � ��������� - �������� ������.","���������� ��������� ������ ������ �� ������ ������ ���������.","� �������, ���� �� ������������ ����� �� ������� ����, � ��� �� ����� �������� ������!",
        "� ����������� ���� ���� ���������� ������� ������ ����, ��� � ���� ��� ����������, ��� ������� �����������."};
        return null;
    }
}
