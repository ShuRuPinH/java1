package ru.progwards.java2.lessons.classloader;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.ProtectionDomain;
import java.util.*;

import javassist.*;
import org.w3c.dom.ls.LSOutput;
import ru.progwards.java1.lessons.datetime.Profiler;
import ru.progwards.java1.lessons.datetime.StatisticInfo;


public class ProfilerTrans implements ClassFileTransformer {

    List args = null;
    static String classPath = "";
    public static Profiler profiler = new Profiler();


    public ProfilerTrans(String argus) {

        if (argus != null) this.args = new ArrayList(Arrays.asList(argus.split(";")));

    }

    @Override
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer
    ) {
        // System.out.println(className);
        String[] names = className.split("/");
        String claName = names[names.length - 1];
        //  System.out.println(className);

        if (args.contains(claName)) {


            System.out.println("ProfilerTrans: --- " + className);


            ClassPool classPool = ClassPool.getDefault();
            classPool.importPackage("ru.progwards.java1.lessons.datetime.Profiler");
            classPool.importPackage("ru.progwards.java1.lessons.datetime.StatisticInfo");
            String clasProf = this.getClass().toString().replace("class ", "") + ".profiler";
            System.out.println("classProf  : " + clasProf);

            try {

                CtClass ctClass = classPool.get(className.replace('/', '.'));
                // System.out.println(ctClass.toString());


                for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {

                    String tmpName = ctMethod.getName();


                    //  System.out.println("metod:" + tmpName + "   empty=" + ctMethod.isEmpty());

                    ctMethod.insertBefore("{" + clasProf +
                            ".enterSection(\"" + tmpName + "\");}");

                    if (tmpName.equals("main")) {
                        ctMethod.insertAfter("{" + clasProf +
                                ".exitSection(\"" + tmpName + "\");" +
                                clasProf.replace(".profiler", "") + ".printStatisticInfo(\"" + claName + ".stat\");" +
                                "}");

                        classPath = ctClass.getName();

                    } else
                        ctMethod.insertAfter("{" + clasProf +
                                ".exitSection(\"" + tmpName + "\");}");


                }
                return ctClass.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void printStatisticInfo(String fileName) {
        var list = profiler.getStatisticInfo();
        Path path = Paths.get(fileName);

        try {
            System.out.println("fn=" + fileName);


            Files.deleteIfExists(path);
            Files.createFile(path);

            for (StatisticInfo u : list) {
                Files.writeString(path, u.toString() + "\n", StandardOpenOption.APPEND);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

