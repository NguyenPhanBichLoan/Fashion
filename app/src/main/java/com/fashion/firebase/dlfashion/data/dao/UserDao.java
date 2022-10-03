package com.fashion.firebase.dlfashion.data.dao;

import com.fashion.firebase.dlfashion.data.model.User;

import java.util.List;

public interface UserDao {

    User find(int id);

    List<User> all();

    void insert(User user);

    void update(User user);

    void delete(int id);

    List<User> findByName(String name);

    User checkLogin(String username, String password);
}
