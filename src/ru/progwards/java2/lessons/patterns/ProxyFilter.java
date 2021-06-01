package ru.progwards.java2.lessons.patterns;

import java.util.LinkedList;

public final class ProxyFilter {
    private static ProxyFilter instance;

    private LinkedList<GPS> pointList;

    double lastMLat = 0;
    double lastDLat = 0;


    double lastMLon = 0;
    double lastDLon = 0;

    int count = 1;

    public synchronized static ProxyFilter getInstance() {
        if (instance == null) instance = new ProxyFilter();

        return instance;
    }

    @Override
    public String toString() {
        return "ProxyFilter{" +
                "count=" + count +
                "\tlastMLat=" + lastMLat +
                ", lastDLat=" + lastDLat +
                ", lastMLon=" + lastMLon +
                ", lastDLon=" + lastDLon +

                '}';
    }

    GPS filter(GPS point) {

        lastDLat = ((count - 1) * lastDLat / count) + ((count - 1) * (point.lat - lastMLat) * (point.lat - lastMLat)) / (count * count);
        lastDLon = ((count - 1) * lastDLon / count) + ((count - 1) * (point.lon - lastMLon) * (point.lat - lastMLat)) / (count * count);

        lastMLat = ((count - 1) * lastMLat / count) + (point.lat / count);
        lastMLon = ((count - 1) * lastMLon / count) + (point.lon / count);

        System.out.println(toString());

        double sigmaLat = Math.sqrt(lastDLat);
        double sigmaLon = Math.sqrt(lastDLon);

        // System.out.println("sigmaLat = "+ sigmaLat+ "             sigmaLon = "+sigmaLon);
        if (count >= 50) {
            if (point.lat > (lastMLat + (3 * sigmaLat)) || point.lat < (lastMLat - (3 * sigmaLat)) ||
                    point.lon > (lastMLon + (3 * sigmaLon)) || point.lon < (lastMLon - (3 * sigmaLon))) {
                return null;
            }

        }

        count++;

        return point;


    }


    public static void main(String[] args) {
        ProxyFilter pf = ProxyFilter.getInstance();

        for (int i = 0; i < 100; i++) {
            if (i == 77) {
                GPS tmp = pf.filter(new GPS(110, 2, i + 1));
                System.out.println(tmp);
            } // 109 еще попадает

            else {

                GPS tmp = pf.filter(new GPS(i, i, i + 1));
                System.out.println(tmp);
            }

        }
        // 5, 1, 6, 2, 12
/*        pf.test(5);
        pf.test(1);
        pf.test(6);
        pf.test(2);
        pf.test(12);*/

    }

    void test(double nn) {
        GPS tmp = filter(new GPS(nn, nn, 0 + 1));
        System.out.println(tmp + "count = " + (count - 1));
    }
}
