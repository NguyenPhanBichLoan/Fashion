package com.fashion.firebase.dlfashion.data;

import android.content.Context;

import com.fashion.firebase.dlfashion.data.dao.CartDao;
import com.fashion.firebase.dlfashion.data.dao.CategoryDao;
import com.fashion.firebase.dlfashion.data.dao.ProductDao;
import com.fashion.firebase.dlfashion.data.dao.UserDao;
import com.fashion.firebase.dlfashion.data.implement.CartDaoImplement;
import com.fashion.firebase.dlfashion.data.implement.CategoryDaoImplement;
import com.fashion.firebase.dlfashion.data.implement.ProductDaoImplement;
import com.fashion.firebase.dlfashion.data.implement.UserDaoImplement;

public class DatabaseSQLite extends DatabaseDao {

    private final Context context;

    public DatabaseSQLite(Context context) {
        this.context = context;
    }

    @Override
    public CategoryDao getCategoryDao() {
        return new CategoryDaoImplement(context);
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImplement(context);
    }

    @Override
    public ProductDao getProductDao() {
        return new ProductDaoImplement(context);
    }

    @Override
    public CartDao getCartDao() {
        return new CartDaoImplement(context);
    }
}
