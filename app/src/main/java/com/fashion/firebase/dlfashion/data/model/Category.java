package com.fashion.firebase.dlfashion.data.model;

public class Category {

    private int id;
    private String name;
    private String description;
    private String image;
    private int status;

    public Category() {
    }

    public Category(int id, String name, String description, String image, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
