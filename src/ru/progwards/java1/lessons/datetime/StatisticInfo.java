package ru.progwards.java1.lessons.datetime;


public class StatisticInfo implements Comparable<StatisticInfo> {

    public String sectionName;
    public int fullTime;
    public int selfTime;
    public int count;

    @Override
    public String toString() {
        return "StatisticInfo{" +
                "sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                '}';
    }

    public StatisticInfo(String sectionName, int fullTime, int selfTime, int count) {
        this.sectionName = sectionName;
        this.fullTime = fullTime;
        this.selfTime = selfTime;
        this.count = count;
    }

    @Override
    public int compareTo(StatisticInfo o) {
        return this.sectionName.compareTo(o.sectionName);
    }
}