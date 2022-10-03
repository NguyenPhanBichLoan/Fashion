package com.fashion.firebase.dlfashion.data.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;
    private String image;
    private double coins;
    private int status;

    public User() {
    }

    public User(int id, String username, String password, String phone, String email, String address, String image, double coins, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.image = image;
        this.coins = coins;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
