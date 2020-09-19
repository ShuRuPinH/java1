package ru.progwards.java1.lessons.files;

import java.util.List;

public class OrderItem implements Comparable {

    public String googsName;//- наименование товара
    public int count;// - количество
    public double price;// - цена за единицу

    public String getGoogsName() {
        return googsName;
    }

    public void setGoogsName(String googsName) {
        this.googsName = googsName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public int compareTo(Object o) {
        return CharSequence.compare(this.googsName, ((OrderItem) o).googsName);
    }
}
