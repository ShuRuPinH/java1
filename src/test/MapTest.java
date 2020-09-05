package test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
}
