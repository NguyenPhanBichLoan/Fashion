package com.fashion.firebase.dlfashion.data.model;

import java.io.Serializable;
import java.text.NumberFormat;

public class Product implements Serializable {

    private int id;
    private int category;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String image;
    private String group;
    private int status;
    private int view;

    public Product() {}

    public Product(int id, int category, String name, String description, double price, int quantity, String image, String group, int status, int view) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.group = group;
        this.status = status;
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
}

