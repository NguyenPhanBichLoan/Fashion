package com.fashion.firebase.dlfashion.data.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fashion.firebase.dlfashion.data.DatabaseHelper;
import com.fashion.firebase.dlfashion.data.dao.ProductDao;
import com.fashion.firebase.dlfashion.data.model.Product;
import com.fashion.firebase.dlfashion.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImplement extends DatabaseHelper implements ProductDao {

    public ProductDaoImplement(Context context) {
        super(context);
    }

    @Override
    public Product find(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TB_PRODUCTS, null, Constants.TB_PRODUCTS_ID + "=?", new String[]
                {String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setCategory(cursor.getInt(1));
        product.setName(cursor.getString(2));
        product.setDescription(cursor.getString(3));
        product.setPrice(cursor.getDouble(4));
        product.setQuantity(cursor.getInt(5));
        product.setImage(cursor.getString(6));
        product.setGroup(cursor.getString(7));
        product.setStatus(cursor.getInt(8));
        product.setView(cursor.getInt(9));
        return product;
    }

    @Override
    public List<Product> all() {
        return null;
    }

    @Override
    public void insert(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.TB_PRODUCTS_CATEGORY, product.getCategory());
        values.put(Constants.TB_PRODUCTS_NAME, product.getName());
        values.put(Constants.TB_PRODUCTS_DESCRIPTION, product.getDescription());
        values.put(Constants.TB_PRODUCTS_PRICE, product.getPrice());
        values.put(Constants.TB_PRODUCTS_IMAGE, product.getImage());
        values.put(Constants.TB_PRODUCTS_GROUPS, product.getGroup());
        db.insert(Constants.TB_PRODUCTS, null, values);
    }

    @Override
    public void update(Product category) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public List<Product> findByTop8View() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> productsList = new ArrayList<>();
        String query = "SELECT * FROM " + Constants.TB_PRODUCTS + " ORDER BY " + Constants.TB_PRODUCTS_VIEWS + " DESC LIMIT 8";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setCategory(cursor.getInt(1));
            product.setName(cursor.getString(2));
            product.setDescription(cursor.getString(3));
            product.setPrice(cursor.getDouble(4));
            product.setQuantity(cursor.getInt(5));
            product.setImage(cursor.getString(6));
            product.setGroup(cursor.getString(7));
            product.setStatus(cursor.getInt(8));
            product.setView(cursor.getInt(9));
            productsList.add(product);
            cursor.moveToNext();
        }
        return productsList;
    }

    @Override
    public List<Product> findByCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> productsList = new ArrayList<>();
        String query = "SELECT * FROM " + Constants.TB_PRODUCTS + " AS a"
                + " WHERE a." + Constants.TB_PRODUCTS_CATEGORY + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setCategory(cursor.getInt(1));
            product.setName(cursor.getString(2));
            product.setDescription(cursor.getString(3));
            product.setPrice(cursor.getDouble(4));
            product.setQuantity(cursor.getInt(5));
            product.setImage(cursor.getString(6));
            product.setGroup(cursor.getString(7));
            product.setStatus(cursor.getInt(8));
            product.setView(cursor.getInt(9));
            productsList.add(product);
            cursor.moveToNext();
        }
        return productsList;
    }
}

