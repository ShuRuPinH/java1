package ru.progwards.java1.lessons.maps;

import java.io.File;
import java.util.*;

public class SalesInfo {
    private ArrayList<String> fileSts = new ArrayList();


    public int loadOrders(String fileName) {
        File file;
        int count = 0;

        if (fileName != null) file = new File(fileName);
        else return 0;
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String temp = scan.nextLine();
                System.out.println(temp);
                Scanner wordScn = new Scanner(temp).useDelimiter("\\s*,\\s*");

                if (wordScn.hasNext()) wordScn.next();
                else continue;
                if (wordScn.hasNext()) wordScn.next();
                else continue;
                if (wordScn.hasNextInt()) wordScn.next();
                else continue;
                if (wordScn.hasNextDouble()) {
                    fileSts.add(temp);
                    count++;
                } else continue;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
/*
3.2 Реализовать метод public Map<String, Double> getGoods() - вернуть список товаров,
отсортированный по наименованию товара.
 В String - наименование товара, в Double - общая сумма продаж по товару
 */

    public Map<String, Double> getGoods() {
        TreeMap<String, Double> gGoods = new TreeMap<>();

        for (String arr : fileSts) {
            try (Scanner wordScn = new Scanner(arr).useDelimiter("\\s*,\\s*")) {
                while (wordScn.hasNextLine()) {
                    wordScn.next();
                    String tG = wordScn.next();
                    wordScn.next();
                    Double tS = Double.valueOf(wordScn.next());

                    if (gGoods.containsKey(tG)) gGoods.put(tG, (gGoods.get(tG) + tS));
                    else gGoods.put(tG, tS);

                }


            }
        }
        return gGoods;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        TreeMap<String, AbstractMap.SimpleEntry<Double, Integer>> gCust = new TreeMap<>();

        for (String arr : fileSts) {
            try (Scanner wordScn = new Scanner(arr).useDelimiter("\\s*,\\s*")) {
                while (wordScn.hasNextLine()) {
                    String tN = wordScn.next();
                    wordScn.next();
                    Integer qN = Integer.valueOf(wordScn.next());
                    Double tS = Double.valueOf(wordScn.next());

                    if (gCust.containsKey(tN)) {
                        AbstractMap.SimpleEntry aMap = new AbstractMap.SimpleEntry(gCust.get(tN));
                        qN += (Integer) aMap.getValue();
                        tS += (Double) aMap.getKey();
                        AbstractMap.SimpleEntry aMap2 = new AbstractMap.SimpleEntry(tS, qN);

                        gCust.put(tN, aMap2);
                    } else {
                        gCust.put(tN, new AbstractMap.SimpleEntry(tS, qN));
                    }

                }


            }
        }
        return gCust;

    }

    public static void main(String[] args) {
        SalesInfo test = new SalesInfo();
        System.out.println(test.loadOrders("test.csv"));

        System.out.println(test.getGoods());
        System.out.println(test.getCustomers());
    }

}
/*
Задача 3, Класс SalesInfo
Информация по продажам

Во входном файле находятся данные в CSV формате, CSV - Comma Separated Values, значения разделенные запятыми.
Каждая строка - данные об одной покупке. Входные данные

ФИ покупателя, наименование товара, количество, сумма

String, String, int, double


Пример


Иванов Сергей, iPhone 10X, 2, 150000
Петрова Анна, наушники JBL, 2, 7000
Иванов Сергей, чехол для iPhone, 1, 1000
Петрова Анна, пакет пластиковый, 1, 3
Радж Кумар, батарейка ААА, 1, 150
Михаил Цикло, iPhone 10X, 1, 75000
Радж Кумар, пакет пластиковый, 1, 3
Михаил Цикло, пакет пластиковый, 1, 3
Иванов Сергей, стекло защитное, 1, 1000
Василий Пупкин, спички, 10, 10

…


3.1 Реализовать метод public int loadOrders(String fileName)
- вернуть количество успешно загруженных строк. Если в строке более или менее 4-x полей,
или количество и сумма не преобразуются в числа - эту строку не загружаем.

3.2 Реализовать метод public Map<String, Double> getGoods() - вернуть список товаров,
отсортированный по наименованию товара.
 В String - наименование товара, в Double - общая сумма продаж по товару

3.3 Реализовать метод public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() - вернуть
список покупателей, отсортированный по алфавиту. В String  - ФИ, в Double - сумма всех покупок покупателя,
 в Integer - количество покупок

3.4 Протестировать на данных из примера выше
 */