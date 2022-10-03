package com.fashion.firebase.dlfashion.data.dao;

import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public interface ProductDao {

    Product find(int id);

    List<Product> all();

    void insert(Product product);

    void update(Product product);

    void delete(int id);

    List<Product> findByName(String name);

    List<Product> findByTop8View();

    List<Product> findByCategory(int id);
}
