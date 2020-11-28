package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    List<Goods> list = new ArrayList<>();


    public void setGoods(List<Goods> list) {
        this.list = list;

    }

    public List<Goods> sortByName() {
        list.sort((a, b) -> a.name.compareTo(b.name));
        return list;
    }

    public List<Goods> sortByNumber() {
        list.sort((a, b) -> a.number.compareTo(b.number));
        return list;
    }

    public List<Goods> sortByPartNumber() {
        list.sort((a, b) -> (a.number).substring(0, 3).compareTo((b.number).substring(0, 3)));
        return list;
    }

    public List<Goods> sortByAvailabilityAndNumber() {

        list.sort((a, b) -> a.available == b.available ? a.number.compareTo(b.number) : Integer.compare(a.available, b.available));
        return list;
    }

    public List<Goods> expiredAfter(Instant date) {
        list.sort((a, b) -> (a.expired).compareTo((b.expired)));
        var temp = list.stream().takeWhile(x -> x.expired.isBefore(date)).collect(Collectors.toList());
        return temp;
    }

    public List<Goods> countLess(int count) {
        list.sort((a, b) -> Integer.compare(a.available, b.available));
        List<Goods> temp = list.stream().takeWhile(x -> x.available < count).collect(Collectors.toList());
        return temp;
    }

    public List<Goods> countBetween(int count1, int count2) {
        Predicate<Goods> gre = b -> b.available > count1;
        Predicate<Goods> bel = b -> b.available < count2;

        Predicate<Goods> betw = gre.and(bel);

        list.sort((a, b) -> Integer.compare(a.available, b.available));
        return list.stream().filter(betw).collect(Collectors.toList());

    }

    public static void main(String[] args) {
        GoodsWithLambda test = new GoodsWithLambda();

        List<Goods> listt = new ArrayList<>();

        for (int i = 7; i > 0; i--) {
            Goods tG = new Goods("G" + i + "-ood");

            if (i == 5 || i == 7) {
                tG.setAvailable(55);
            }
            listt.add(tG);
        }

        listt.forEach(element -> System.out.println(element));

        test.setGoods(listt);

        System.out.println("**** sortByName ****");
        test.sortByName().forEach(element -> System.out.println(element));

        System.out.println("**** sortByNumber ****");
        test.sortByNumber().forEach(element -> System.out.println(element));

        System.out.println("**** sortByPartNumber ****");
        test.sortByPartNumber().forEach(element -> System.out.println(element));

        System.out.println("**** sortByAvailabilityAndNumber() ****");
        test.sortByAvailabilityAndNumber().forEach(element -> System.out.println(element));

        System.out.println("**** expiredAfter" + Instant.now().plusSeconds((5 * 60 * 60 * 24)).toString().substring(0, 19) + " ****");
        test.expiredAfter(Instant.now().plusSeconds((5 * 60 * 60 * 24))).forEach(element -> System.out.println(element));

        System.out.println("**** countLess(40) ****");
        test.countLess(40).forEach(element -> System.out.println(element));

        System.out.println("**** countBetween(10,50) ****");
        test.countBetween(10, 50).forEach(element -> System.out.println(element));


    }
}
