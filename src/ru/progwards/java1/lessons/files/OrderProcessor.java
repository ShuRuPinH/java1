package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static java.nio.file.Files.getLastModifiedTime;
import static java.nio.file.Files.isDirectory;

public class OrderProcessor {
    String pathMain;
    LocalDate time;
    int count = 0;
    List<Order> ordersLL = new ArrayList<>();

    //3.3 конструктор public OrderProcessor(String startPath) - инициализирует класс, с указанием начальной папки для хранения файлов
    public OrderProcessor(String startPath) {
        this.pathMain = startPath;
    }

    /*    3.4 метод public int loadOrders(LocalDate start, LocalDate finish, String shopId) - загружает заказы за указанный диапазон дат,
        с start до finish, обе даты включительно. Если start == null, значит нет ограничения по дате слева, если finish == null,
        значит нет ограничения по дате справа, если shopId == null - грузим для всех магазинов
        При наличии хотя бы одной ошибке в формате файла, файл полностью игнорируется, т.е. Не поступает в обработку.
        Метод возвращает количество файлов с ошибками. При этом, если в классе содержалась информация, ее надо удалить*/
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {

        if (shopId == null) shopId = "???";
        if (start == null) start = LocalDate.of(2008, 12, 03);
        if (finish == null) finish = LocalDate.of(3000, 12, 12);
        String pathStr = "glob:**/" + shopId + "-??????-????.csv";

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pathStr);
        System.out.println("pathMathcer:" + pathStr + "          pathMain:" + pathMain + "                  shopId" + shopId);
        try {
            LocalDate finalStart = start;
            LocalDate finalFinish = finish;
            Files.walkFileTree(Paths.get(pathMain), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    Instant instTime;

                    if (pathMatcher.matches(Paths.get(pathMain).relativize(path))) {
                        try {
                            System.out.println("path:" + path);
                            instTime = Files.getLastModifiedTime(path).toInstant();
                            time = LocalDate.ofInstant(instTime, ZoneId.systemDefault());
                            System.out.println("time:" + time);
                            if (time.isAfter(finalStart) && time.isBefore(finalFinish) || time.equals(finalStart) || time.equals(finalFinish)) {
                                ordersLL.add(extractor(path, instTime));
                            }
                        } catch (IOException e) {

                            System.out.println("instan time");
                            //e.printStackTrace();
                        }


                    }

                    System.out.println("выход из удачного волкера");
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    count++;
                    System.out.println("выход из НЕудачного волкера");
                    return FileVisitResult.CONTINUE;
                }


            });
        } catch (IOException e) {
            System.out.println("Walker --");
            // e.printStackTrace();
        }
       /* pathMain = null;
        time = null;*/
        int res = count;
        count = 0;
        System.out.println("КОНЕЦ волкера");
        return res;
    }

    /*
    Информация о заказах поступает в виде файлов, которые лежат в под-папках разбитых по неделям, имена папок не имеют значения.
    Имя каждого файла имеет формат: AAA-999999-ZZZZ.csv где AAA - обязательные 3 символа shopId - идентификатор магазина,
    999999 - обязательные 6 символов orderId - номер заказа, ZZZZ - обязательные 4 символа customerId - идентификатор покупателя,
    расширение файла - csv, например S02-P01X12-0012.csv: shopId=”S02”, orderId=”P01X12”, customerId=”0012”
    Содержимое каждого файла имеет формат CSV (Comma Separated Values) со следующими данными
    Наименование товара, количество, цена за единицу
    01234567890123456
    AAA-999999-ZZZZ.csv
    Например:

    Игрушка мягкая “Мишка”, 1, 1500
    Пазл “Замок в лесу”, 2, 700
    Книжка “Сказки Пушкина”, 1, 300
    */
    static Order extractor(Path pth, Instant tim) {
        System.out.println("ЭКСТРАКТОР" + "   pth:" + pth + "    tim:" + tim);
        Order ord = new Order();
        List<OrderItem> lItm = new ArrayList<>();
        List<String> lString = null;

        String nameFile = pth.getFileName().toString();
        System.out.println("парсинг названия :" + nameFile);
        ord.setShopId(nameFile.substring(0, 2));

        ord.setOrderId(nameFile.substring(4, 9));

        ord.setCustomerId(nameFile.substring(11, 14));
        ord.setDatetime(LocalDateTime.ofInstant(tim, ZoneId.systemDefault()));
        System.out.println(" перед файла в строку");
        try {
            lString = Files.readAllLines(pth);



        } catch (IOException e) {
            System.out.println("@@@@@@@@@           extractor error");
        }
        System.out.println(" перед итератором");
        Iterator<String> iter = lString.iterator();
        System.out.println("0");
        while (iter.hasNext()) {
            System.out.println("1");
            OrderItem temp = new OrderItem();
            System.out.println("2");
            Scanner scan = new Scanner(iter.next());
            System.out.println("3");
            scan.useDelimiter(",");
            System.out.println("4");
            temp.setGoogsName(scan.next());
            System.out.println("5");
            temp.setCount(Integer.parseInt(scan.next()));
            System.out.println("6");
            temp.setPrice(Double.parseDouble(scan.next()));
            System.out.println("******** OredrItem:" + temp.toString());
            lItm.add(temp);
            scan.close();

        }
        System.out.println("после итератора");
        //lItm.sort(null);
        ord.setItems(lItm);
        System.out.println("**********************  ENd  *****************************");
        return ord;

    }

    /*
    3.5 метод public List<Order> process(String shopId) - выдать список заказов в порядке обработки (отсортированные по дате-времени),
    для заданного магазина. Если shopId == null, то для всех
     */
    public List<Order> process(String shopId) {
        List<Order> res = new ArrayList<>();
        Comparator dateComparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getDatetime().compareTo(o2.getDatetime());
            }
        };


        for (Order order : ordersLL) {
            if (shopId == null) res.add(order);
            if (shopId != null && shopId.equals(order.getShopId())) res.add(order);
        }
        res.sort(dateComparator);
        return res;


    }

    /*
    3.6 метод public Map<String, Double> statisticsByShop() - выдать информацию по объему продаж по магазинам (отсортированную по ключам):
    String - shopId, double - сумма стоимости всех проданных товаров в этом магазине
    */
    public Map<String, Double> statisticsByShop() {
        Map<String, Double> res = new TreeMap<>();
        for (Order order : ordersLL) {
            if (res.containsKey(order.getShopId())) {
                double ts = res.get(order.getShopId()) + order.getSum();
                res.put(order.getShopId(), ts);
            } else {
                res.put(order.getShopId(), order.getSum());
            }
        }
        return res;
    }

    /*
    3.7 метод public Map<String, Double> statisticsByGoods() - выдать информацию по объему продаж по товарам (отсортированную по ключам):
    String - goodsName, double - сумма стоимости всех проданных товаров этого наименования
     */
    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> res = new TreeMap<>();
        for (Order order : ordersLL) {

            for (OrderItem ordItem : order.getItems()) {
                if (res.containsKey(ordItem.getGoogsName())) {
                    double gs = res.get(ordItem.getGoogsName()) + ordItem.getPrice() * ordItem.getCount();
                    res.put(ordItem.getGoogsName(), gs);
                } else {
                    res.put(ordItem.getGoogsName(), ordItem.getPrice() * ordItem.getCount());
                }
            }
        }
        return res;
    }

    /*
    3.8 метод public Map<LocalDate, Double> statisticsByDay() - выдать информацию по объему продаж по дням (отсортированную по ключам):
    LocalDate - конкретный день, double - сумма стоимости всех проданных товаров в этот день
    */
    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> res = new TreeMap<>();
        for (Order order : ordersLL) {
            LocalDate ld = (order.getDatetime()).toLocalDate();
            if (res.containsKey(ld)) {
                double ds = res.get(ld) + order.getSum();
                res.put(ld, ds);
            } else {
                res.put(ld, order.getSum());
            }
        }
        return res;
    }


}
