package test;

import java.util.List;

public class Lamda {

    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name + " " + age;
        }


/*
Создайте метод, используя лямбда, с сигнатурой void sortAndPrint(List<Person> list),
который вначале сортирует list по возрасту, а затем выводит его на консоль.
 */

        void sortAndPrint(List<Person> list) {
            list.sort((Person a, Person b) -> Integer.compare(a.age, b.age));
            list.forEach(System.out::println);
        }


    }

    String reverseChars(String str) {
        if (str.length() <= 1) return str;
        return reverseChars(str.substring(1)) + str.substring(0, 1);

    }

    public static void main(String[] args) {
        Lamda trst = new Lamda();
        System.out.println(trst.reverseChars("qwerty"));
    }


}