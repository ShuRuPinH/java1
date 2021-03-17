package ru.progwards.java2.lessons.reflection;

import javax.swing.*;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class GettersAndSetters {

    public static void check(String clazz) {
        Map<String, Type> privF = new HashMap<>();
        Map<String, Type[]> privM = new HashMap<>();

        String className;

        try {
            Class insp = Class.forName(clazz);
            className = insp.getSimpleName();


            Field[] fld = insp.getDeclaredFields();
            for (Field f : fld) {
                if (Modifier.toString(f.getModifiers()).isEmpty()) continue;
                if (Modifier.toString(f.getModifiers()).equals("private")) {
                    privF.put(f.getName(), f.getType());
                }

            }


            Method[] met = insp.getDeclaredMethods();

            for (Method m : met) {
                Type[] typz = new Type[2];

                System.out.println((Modifier.toString(m.getModifiers())));

                if ((Modifier.toString(m.getModifiers())).contains("static")) continue;
                if (!(Modifier.toString(m.getModifiers())).contains("public")) continue;
                if (m.getName().startsWith("set") || m.getName().startsWith("get")) {
                    var par = m.getGenericParameterTypes();
                    if (par.length > 1) continue;
                    else typz[0] = par.length != 0 ? par[0] : null;
                    typz[1] = m.getReturnType();

                    privM.put(m.getName(), typz);
                }
            }

            for (Map.Entry x : privM.entrySet()) {
                System.out.println(x.toString());
            }

            for (Map.Entry<String, Type> s : privF.entrySet()) {
                //   System.out.println("CHECK : "+s);
                boolean get = false;
                boolean set = false;


                for (Map.Entry<String, Type[]> m : privM.entrySet()) {
                    Type[] t = m.getValue();
                    if ((("get" + s.getKey()).toLowerCase()).equals(m.getKey().toLowerCase()) && t[1].equals(s.getValue())) {
                        if (t[0] == null) get = true;
                        // System.out.println("have getter " +s.getKey());

                    }
                    //  System.out.println("!!!!    "+m.getKey() + "  Param=" + t[0] + "  Return=" + t[1]);
                    if ((("set" + s.getKey()).toLowerCase()).equals(m.getKey().toLowerCase()) && t[0].equals(s.getValue())) {
                        set = true;
                    }

                }

                if (get == false) {
                    System.out.println("public " + s.getValue().toString().replace("class java.lang.", "") + " get" + name(s.getKey())
                            + "()");
                }

                if (set == false) {
                    System.out.println("public void" + " set" + name(s.getKey())
                            + "(" + s.getValue().toString().replace("class java.lang.", "") + " " + s.getKey() + ")");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static String name(String nn) {
        var tmp = nn.substring(1);
        return nn.substring(0, 1).toUpperCase() + tmp;
    }

    public static void main(String[] args) {
        check("ru.progwards.java2.lessons.reflection.Person");


    }
}


