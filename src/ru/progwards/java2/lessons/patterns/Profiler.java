package ru.progwards.java2.lessons.patterns;

import ru.progwards.java1.lessons.datetime.StatisticInfo;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public final class Profiler {
    private static volatile Profiler instance;


    private ConcurrentSkipListMap<String, Instant> now = new ConcurrentSkipListMap();
    private ConcurrentSkipListMap<String, AbstractMap.SimpleEntry<Duration, Integer>> main = new ConcurrentSkipListMap();
    private volatile HashMap<String, Set> emb = new HashMap<>();

    private Profiler() {
    }

    public static Profiler getInstance() {
        if (instance == null) {
            synchronized (Profiler.class) {
                //использование двойной проверки в Singleton для многопоточности
                if (instance == null) {
                    instance = new Profiler();
                }
            }
        }
        return instance;
    }


    public void enterSection(String name) {
        var st = Instant.now();
        now.put(name, st);
    }

    public void exitSection(String name) {
        var ex = Instant.now();
        Duration temp = Duration.between(now.get(name), ex);

        if (main.containsKey(name)) {
            AbstractMap.SimpleEntry<Duration, Integer> ttm = new AbstractMap.SimpleEntry(main.get(name));
            temp = temp.plus(ttm.getKey());
            main.put(name, new AbstractMap.SimpleEntry<>(temp, ttm.getValue() + 1));
        } else main.put(name, new AbstractMap.SimpleEntry<>(temp, 1));
        now.remove(name);
        if (now.isEmpty() == false) {

            Object tt = now.lastKey();
            String stt = String.valueOf(tt);

            if (emb.containsKey(stt)) {
                Set s = new HashSet(emb.get(stt));
                s.add(name);
                emb.put(stt, s);
            } else emb.put(stt, Set.of(name));


        }


    }
    /*
    public String sectionName; - имя секции
    public int fullTime - полное время выполнения секции в миллисекундах.
    public int selfTime - чистое время выполнения секции в миллисекундах.
    Для вложенных секций, из времени выполнения внешней секции нужно вычесть времена выполнения вложенных секций.

    public int count - количество вызовов. В случае, если вызовов более одного,
    fullTime и selfTime содержат суммарное время выполнения всех вызовов.
     */

    public List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> res = new ArrayList<>();

        Set set = main.entrySet();
        Iterator<Map> itr = set.iterator();
        while (itr.hasNext()) {
            //  NAME  //////////////////////
            Map.Entry temp = (Map.Entry) itr.next();
            String secName = String.valueOf(temp.getKey());
            // FULL TIME  ////////////////////////////////
            AbstractMap.SimpleEntry abstemp = (AbstractMap.SimpleEntry) temp.getValue();
            Duration d = (Duration) abstemp.getKey();
            int fullTime = (int) d.toMillis();
            // SELF TIME  //////////////////////////////////
            int selfTim = fullTime;
            if (emb.containsKey(secName)) {


                Iterator<Set> iter = emb.get(secName).iterator();
                while (iter.hasNext()) {
                    String tName = String.valueOf(iter.next());


                    Map.Entry ent = main.get(tName);
                    selfTim = selfTim - (int) ((Duration) ent.getKey()).toMillis();
                }
            }
            //COUNT  //////////////////////////////////////
            int cnt = (int) abstemp.getValue();
            // make class ////
            res.add(new StatisticInfo(secName, fullTime, selfTim, cnt));
        }
        res.sort(null);
        return res;
    }

    public void main(String[] args) {

        Profiler re = Profiler.getInstance();
        enterSection("sec1");
        enterSection("sec2");
        for (int i = 0; i < 1000; i++) {

            int r = 1 + 2;

        }
        exitSection("sec2");
        enterSection("sec3");
        for (int i = 0; i < 1000; i++) {


            for (int j = 0; j < 1000; j++) {
                enterSection("sec4");
                int f = 1 + 2;

                exitSection("sec4");
            }
            enterSection("sec5");
            for (int j = 0; j < 1000; j++) {
                enterSection("secSUB5");
                double m = 0;
                m++;

                exitSection("secSUB5");
            }
            exitSection("sec5");

        }
        exitSection("sec3");
        exitSection("sec1");


        for (StatisticInfo u : getStatisticInfo()) {
            System.out.println(u.toString());

        }

    }


}
