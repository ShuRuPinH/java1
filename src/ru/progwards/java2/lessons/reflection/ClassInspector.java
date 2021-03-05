package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ClassInspector {


    public static void inspect(String clazz) {
        List<String> page = new ArrayList<>();
        String className;

        try {
            Class insp = Class.forName(clazz);
            className = insp.getSimpleName();
            page.add("class " + className + " {");

            Field[] fld = insp.getDeclaredFields();
            for (Field f : fld) {
                String tmp = "";
                tmp = (Modifier.toString(f.getModifiers()).isEmpty() ? "" : Modifier.toString(f.getModifiers()) + " ");

                tmp += "" + f.getType().toString().replace("class java.lang.", "") + " " + f.getName() + ";";


                page.add("    " + tmp);
            }


            Constructor[] cons = insp.getDeclaredConstructors();
            int cnt = 0;
            for (Constructor c : cons) {
                String tmp = "";
                tmp = (Modifier.toString(c.getModifiers()).isEmpty() ? "" : Modifier.toString(c.getModifiers()) + " ") + className;
                tmp += "(";
                for (Type t : c.getGenericParameterTypes()) {
                    tmp += t.getTypeName().replace("java.lang.", "") + " arg" + (cnt++);
                }
                tmp += ") {}";
                page.add("    " + tmp);
            }

            Method[] met = insp.getDeclaredMethods();
            int cntM = 0;
            for (Method m : met) {
                String tmp = "";
                tmp = (Modifier.toString(m.getModifiers()).isEmpty() ? "" : Modifier.toString(m.getModifiers()) + " ");

                tmp += m.getReturnType().toString().replace("class java.lang.", "") + " ";


                tmp += m.getName() + "(";
                for (Type t : m.getGenericParameterTypes()) {
                    tmp += t.getTypeName().replace("java.lang.", "") + " arg" + (cntM++);
                }
                tmp += ") {}";
                page.add("    " + tmp);
            }
            page.add("}");

            Path fl = Paths.get(className + ".java");
            Files.deleteIfExists(fl);
            Files.createFile(fl);
            for (String s : page) {
                Files.writeString(fl, s + "\n", StandardOpenOption.APPEND);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        inspect("ru.progwards.java2.lessons.reflection.Person");


    }
}
