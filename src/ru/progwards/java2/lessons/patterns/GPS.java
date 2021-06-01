package ru.progwards.java2.lessons.patterns;

public class GPS {
    public double lat; // широта
    public double lon; // долгота
    public long time; // время в мс

    @Override
    public String toString() {
        return "GPS{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", time=" + time +
                '}';
    }

    public GPS(double lat, double lon, long time) {
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }
}