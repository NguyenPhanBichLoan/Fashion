package com.fashion.firebase.dlfashion.data.model;

import java.text.NumberFormat;

public class Item {

    private int product;
    private int quantity;

    public Item() {
    }

    public Item(int product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
