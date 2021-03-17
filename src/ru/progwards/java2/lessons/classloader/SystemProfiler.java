package ru.progwards.java2.lessons.classloader;

import java.lang.instrument.Instrumentation;

public class SystemProfiler {


    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("SystemProfiler: premain стартовал");
        System.out.println("SystemProfiler  args2: " + agentArgument);
        instrumentation.addTransformer(new ProfilerTrans(agentArgument));

    }
}


