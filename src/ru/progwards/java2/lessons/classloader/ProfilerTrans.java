package ru.progwards.java2.lessons.classloader;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.*;

import javassist.*;


public class ProfilerTrans implements ClassFileTransformer {

    List args;


    public ProfilerTrans(String argus) {

        this.args = new ArrayList(Arrays.asList(argus.split(";")));

    }

    @Override
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer
    ) {
        if (className.contains("Profiler")) {

            System.out.println("ProfilerTrans: --- " + className);


            ClassPool classPool = ClassPool.getDefault();
            classPool.importPackage("ru.progwards.java1.lessons.datetime.Profiler");
            classPool.importPackage("ru.progwards.java1.lessons.datetime.StatisticInfo");

            try {
                ;


                CtClass ctClass = classPool.get(className.replace('/', '.'));
                // System.out.println(ctClass.toString());
                CtField f = CtField.make("public Profiler prof= new Profiler();", ctClass);
                ctClass.addField(f);
                CtMethod ctMethod = ctClass.getDeclaredMethod("main"); // имя метода

                System.out.println("metod:" + ctMethod);

                ctMethod.addLocalVariable("start", CtClass.longType);


                ctMethod.insertBefore("{" +
                        "enterSection(\"MAIN\");" +
                        "start = System.currentTimeMillis();  System.out.println(\"вставка в мейн начало:\");}");

                ctMethod.insertAfter("{" +
                        "exitSection(\"MAIN\");" +
                        "System.out.println(\"время выполнения  (конец)\" + (System.currentTimeMillis() - start));" +
                        " System.out.println(\"kj\");" +
                        "}");

                System.out.println(f.getAttribute("main"));

                return ctClass.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

