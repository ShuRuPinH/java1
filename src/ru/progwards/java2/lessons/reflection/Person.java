package ru.progwards.java2.lessons.reflection;

class Person {
    private String name;
    private int age;
    private boolean sex;
    int other;

    public static String getName() {
        return "name";
    }

    public int getAge(int t) {
        return t;
    }

    private void setName(String name) {
    }

    public boolean getSex() {
        return sex;
    }


}

class Greetings {
    void hello() {
    }

    void goodday() {
    }

    void goodnight() {
    }

    void hi() {
    }

    ;
}