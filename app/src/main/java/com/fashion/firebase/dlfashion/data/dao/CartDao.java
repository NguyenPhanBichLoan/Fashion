package com.fashion.firebase.dlfashion.data.dao;

import com.fashion.firebase.dlfashion.data.model.Item;

import java.util.List;

public interface CartDao {

    Item find(int id);

    List<Item> all();

    void insert(Item item);

    void update(Item item);

    void delete(int id);
}
