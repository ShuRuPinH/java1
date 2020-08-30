package ru.progwards.java1.lessons.sets;

import javax.sound.midi.Soundbank;
import java.util.*;

public class ProductAnalytics {


    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }

    public Set<Product> existInAll() {
        List<Product> temp = new ArrayList<>(products);

        for (Shop ich : shops) {
            temp.retainAll(ich.getProducts());

        }
        return new HashSet<>(temp);
    }

    public Set<Product> existAtListInOne() {
        List<Product> temp = new ArrayList<>(products);
        List<Product> temp2 = new ArrayList<>(products);
        for (Shop ich : shops) {
            temp.removeAll(ich.getProducts());
        }
        temp2.removeAll(temp);

        return new HashSet<>(temp2);
    }


    public Set<Product> notExistInShops() {
        List<Product> temp = new ArrayList<>(products);
        for (Shop ich : shops) {
            temp.removeAll(ich.getProducts());
        }
        return new HashSet<>(temp);
    }

    public Set<Product> existOnlyInOne() {
        List<Product> temp = new ArrayList<>(products);
        List<Product> share = new ArrayList<>();
        final ProductAnalytics tmp = this;

        temp.removeAll(tmp.notExistInShops());
        for (Product ichP : temp) {
            int cnt = 0;
            for (Shop ich : shops) {
                if (ich.getProducts().contains(ichP)) cnt++;

            }
            if (cnt == 1) share.add(ichP);
        }


        return new HashSet<>(share);
    }


    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        List<Shop> shop = new ArrayList<>();

        List<Product> ltShop1 = new ArrayList<>();
        List<Product> ltShop2 = new ArrayList<>();
        List<Product> ltShop3 = new ArrayList<>();
        products.add(new Product("Prod #22"));

        for (int i = 1; i <= 12; i++) {
            products.add(new Product("Prod #" + i));
            if (i < 5) {
                ltShop1.add(new Product("Prod #" + i));
                ltShop1.add(new Product("Prod #22"));
            }

            if (i > 2 && i < 7) {
                ltShop2.add(new Product("Prod #22"));
                ltShop2.add(new Product("Prod #" + i));
            }
            if (i > 3 && i < 11) {
                ltShop3.add(new Product("Prod #" + i));
                ltShop3.add(new Product("Prod #22"));
            }
        }
        Shop shop1 = new Shop(ltShop1);
        shop.add(shop1);
        Shop shop2 = new Shop(ltShop2);
        shop.add(shop2);
        Shop shop3 = new Shop(ltShop3);
        shop.add(shop3);

        ProductAnalytics test = new ProductAnalytics(products, shop);

        System.out.println("products=" + products.size());
        System.out.println("not ex in shop=" + test.notExistInShops().size());


        System.out.println("ex in all=" + test.existInAll().size());
        System.out.println("ex in one=" + test.existAtListInOne().size());

        System.out.println("ex in ones=" + test.existOnlyInOne().size());
        System.out.println("products=" + products.size());


    }
}


