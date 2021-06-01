package ru.progwards.java2.lessons.patterns;

import ru.progwards.java1.lessons.bigints.AbsInteger;
import ru.progwards.java1.lessons.bigints.ByteInteger;
import ru.progwards.java1.lessons.bigints.IntInteger;
import ru.progwards.java1.lessons.bigints.ShortInteger;

public enum SingleFactory {
    INSTANCE;
    long temp;
    private AbsInteger value;

/*
-2147483648 до 2147483647

*/

    private AbsInteger getABS(long number) throws Exception {
        if (-128 <= number && number <= 127) value = new ByteInteger((byte) number);
        else if (-32768 <= number && number <= 32767) value = new ShortInteger((short) number);
        else if (-2147483648 <= number && number <= 2147483647) value = new IntInteger((int) number);
        else throw new Exception("Number is too big");
        return value;
    }

    public static void main(String[] args) {
        try {
            SingleFactory test = SingleFactory.INSTANCE;
            System.out.println(test.getABS(21));
            System.out.println(test.getABS(24651));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
