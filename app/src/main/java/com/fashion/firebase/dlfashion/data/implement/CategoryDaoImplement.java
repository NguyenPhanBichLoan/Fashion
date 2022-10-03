package com.fashion.firebase.dlfashion.data.implement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fashion.firebase.dlfashion.data.DatabaseHelper;
import com.fashion.firebase.dlfashion.data.dao.CategoryDao;
import com.fashion.firebase.dlfashion.data.model.Category;
import com.fashion.firebase.dlfashion.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImplement extends DatabaseHelper implements CategoryDao {

    public CategoryDaoImplement(Context context) {
        super(context);
    }

    @Override
    public Category find(int id) {
        return null;
    }

    @Override
    public List<Category> all() {
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TB_CATEGORIES, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));
                Log.i("category", category.getName());
                category.setDescription(cursor.getString(2));
                category.setImage(cursor.getString(3));
                category.setStatus(cursor.getInt(4));
                categoryList.add(category);
                cursor.moveToNext();
            }
        } else {
            return null;
        }
        return categoryList;
    }

    @Override
    public void insert(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Category> findByName(String name) {
        return null;
    }
}
