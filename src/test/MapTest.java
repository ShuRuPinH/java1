package test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.Calendar;

public class MapTest {
    HashMap<Integer, String> a2map(int[] akey, String[] aval) {
        HashMap<Integer, String> res = new HashMap<>();
        for (int i = 0; i < akey.length; i++) {
            res.put(akey[i], aval[i]);
        }
        return res;
    }


    int fillHoles(Map<Integer, String> map, int size) {
        int radd = 0;
        for (int i = 1; i <= size; i++) {
            String oldVal = map.putIfAbsent(i, "Число " + i);
            if (oldVal == null) radd++;

        }
        return radd;

    }


    void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value) {
        if (map.isEmpty()) {
            return;
        } else if (key < map.lastKey() && key > map.firstKey()) {
            map.putIfAbsent(key, value);
        }
    }
/*
Реализуйте метод с сигнатурой void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value),
 который добавляет пару key-value в map при выполнении следующих условий:

значение с таким key должно отсутствовать
значение key долно быть больше головы TreeMap
значение key долно быть меньше хвоста TreeMap
Answer:(penalty regime: 10, 20, ... %)
 */

    /*
    1 января 2020 года, 15 часов и одна наносекунда по Московскому времени
     */
    Instant createInstant() {

        ZonedDateTime res = ZonedDateTime.of(2020, 1, 1, 15, 0, 0, 1, ZoneId.of("Europe/Moscow"));
        return res.toInstant();
    }

    ZonedDateTime parseZDT(String str) {
        String vV = str.substring(30);
        String ar[] = vV.split(" ");

        String mm = str.substring(0, 23);
        String tt = "";

        var zones = ZoneId.getAvailableZoneIds();
        for (String zone : zones) {
            ZoneId z = ZoneId.of(zone);
            String description = z.getDisplayName(TextStyle.FULL, Locale.US);
            if (description.contains(ar[0])) {

                tt = zone;
                break;
            }
        }


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS").withZone(ZoneId.of(tt));
        ZonedDateTime res = ZonedDateTime.from(dtf.parse(mm));
        return res;
    }


    public static void main(String[] args) {
        LocalDateTime ldt2 = LocalDateTime.of(2019, 05, 05, 22, 24);
        System.out.println(ldt2);

        ZoneId zid1 = ZoneId.of("Europe/Moscow");
        System.out.println(zid1.getRules().getOffset(Instant.now()));

        MapTest te = new MapTest();


        String st = "01.01.2020 16:27:14.444 +0300 Moscow Standard Time";

        System.out.println(te.parseZDT(st));

    }
}
