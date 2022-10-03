package com.fashion.firebase.dlfashion.data.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

import com.fashion.firebase.dlfashion.data.DatabaseHelper;
import com.fashion.firebase.dlfashion.data.dao.CartDao;
import com.fashion.firebase.dlfashion.data.model.Item;
import com.fashion.firebase.dlfashion.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CartDaoImplement extends DatabaseHelper implements CartDao {

    public CartDaoImplement(Context context) {
        super(context);
    }

    @Override
    public Item find(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TB_CART,null,Constants.TB_CART_PRODUCT + "=?", new String[]
                {String.valueOf(id)},null,null,null);
        Item item = new Item();
        if (cursor != null && cursor.moveToFirst()) {
            item.setProduct(cursor.getInt(1));
            item.setQuantity(cursor.getInt(2));
        }
        return item;
    }

    @Override
    public List<Item> all() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM " + Constants.TB_CART + " ORDER BY " + Constants.TB_CART_ID + " DESC";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Item item = new Item();
            item.setProduct(cursor.getInt(1));
            item.setQuantity(cursor.getInt(2));
            itemList.add(item);
            cursor.moveToNext();
        }

        if (itemList.size() < 0) {
            return null;
        }

        return itemList;
    }

    @Override
    public void insert(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.TB_CART_PRODUCT,item.getProduct());
        values.put(Constants.TB_CART_QUANTITY,item.getQuantity());
//        values.put(Constants.TB_CATEGORIES_IMAGE, category.getImage());
        db.insert(Constants.TB_CART,null,values);
    }

    @Override
    public void update(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.TB_CART_QUANTITY, item.getQuantity());

        db.update(Constants.TB_CART, values, Constants.TB_CART_PRODUCT + " = ?", new String[] { String.valueOf(item.getProduct()) });
        db.close();
    }

    @Override
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TB_CART, Constants.TB_CART_PRODUCT + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }
}
