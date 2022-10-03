package com.fashion.firebase.dlfashion.data;

import com.fashion.firebase.dlfashion.data.dao.CartDao;
import com.fashion.firebase.dlfashion.data.dao.CategoryDao;
import com.fashion.firebase.dlfashion.data.dao.ProductDao;
import com.fashion.firebase.dlfashion.data.dao.UserDao;
import com.fashion.firebase.dlfashion.data.model.Product;

public abstract class DatabaseDao {

    private static DatabaseDao instance;

    public static void init(DatabaseDao newInstance) {
        instance = newInstance;
    }

    public static DatabaseDao getInstance() {
        return instance;
    }

    public abstract CategoryDao getCategoryDao();

    public abstract UserDao getUserDao();

    public abstract ProductDao getProductDao();

    public abstract CartDao getCartDao();
}
