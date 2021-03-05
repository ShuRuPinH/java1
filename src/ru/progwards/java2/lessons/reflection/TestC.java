package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.*;

public class TestC {

    void callSetName(Person person, String name) {
        try {
            Method met = person.getClass().getDeclaredMethod("setName", String.class);
            met.setAccessible(true);
            met.invoke(person, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Person callConstructor(String name) {
        Person rez = null;
        try {
            Constructor con = Person.class.getDeclaredConstructor(String.class);
            con.setAccessible(true);
            rez = (Person) con.newInstance(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return rez;
    }

    void setName(Person person, String name) {
        try {
            Field nm = person.getClass().getDeclaredField("name");
            nm.setAccessible(true);
            nm.set(person, name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*    void printAnnotation() {
        Method[] methods = Greetings.class.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(AnnotationTest.class)) {
                AnnotationTest annotation = m.getAnnotation(AnnotationTest.class);
                System.out.println(m.getName() + " "+annotation.text());
            }
        }
    }*/


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
       /* Person test = new Person("Tom");
        Person test2 = new Person("Jery");


        TestC tt = new TestC();

        tt.callSetName(test, "Jons");
        Field nm = test.getClass().getDeclaredField("name");

        nm.setAccessible(true);


        System.out.println(nm.get(test));
        System.out.println(nm.get(tt.callConstructor("Piter")));

        tt.setName(test2, "Saimon");
        System.out.println(nm.get(test2));*/

        //tt.printAnnotation();


    }

}
