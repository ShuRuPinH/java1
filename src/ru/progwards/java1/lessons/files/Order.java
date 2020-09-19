package ru.progwards.java1.lessons.files;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    public String shopId;  //- идентификатор магазина
    public String orderId;  // - идентификатор заказа
    public String customerId; // - идентификатор покупателя
    public LocalDateTime datetime; // - дата-время заказа (из атрибутов файла - дата последнего изменения)
    public List<OrderItem> items;// - список позиций в заказе, отсортированный по наименованию товара
    public double sum;  //- сумма стоимости всех позиций в заказе

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getSum() {
        return sum;
    }

    public void setSum() {
        for (OrderItem oI : items) {
            sum += oI.count * oI.price;
        }

    }


}
