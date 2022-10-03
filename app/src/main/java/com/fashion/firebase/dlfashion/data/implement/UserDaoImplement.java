package com.fashion.firebase.dlfashion.data.implement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fashion.firebase.dlfashion.data.DatabaseHelper;
import com.fashion.firebase.dlfashion.data.dao.UserDao;
import com.fashion.firebase.dlfashion.data.model.User;
import com.fashion.firebase.dlfashion.utils.Constants;

import java.util.List;

public class UserDaoImplement extends DatabaseHelper implements UserDao {

    public UserDaoImplement(Context context) {
        super(context);
    }

    @Override
    public User find(int id) {

        return null;
    }

    @Override
    public List<User> all() {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public User checkLogin(String username, String password) {
        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Constants.TB_USERS_USERNAME + " =?" + " AND " + Constants.TB_USERS_PASSWORD + " =?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(Constants.TB_USERS, null, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setPhone(cursor.getString(3));
            user.setEmail(cursor.getString(4));
            user.setAddress(cursor.getString(5));
            user.setImage(cursor.getString(6));
            String coins = cursor.getString(7);
            user.setCoins(Double.parseDouble(coins));
            user.setStatus(cursor.getInt(8));
        } else {
            return null;
        }
        return user;
    }
}
