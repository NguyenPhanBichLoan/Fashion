package com.fashion.firebase.dlfashion.ui.constract;

import android.content.Context;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.DatabaseSQLite;
import com.fashion.firebase.dlfashion.data.model.User;

public class LoginPresenter implements LoginConstract.IPresenter {

    private LoginConstract.IView mView;

    public LoginPresenter(Context context) {
        DatabaseDao.init(new DatabaseSQLite(context));
    }

    @Override
    public void setView(LoginConstract.IView view) {
        this.mView = view;
    }

    @Override
    public void checkLogin(String username, String password) {
        User user = DatabaseDao.getInstance().getUserDao().checkLogin(username, password);
        if (user == null) {
            mView.failure();
        } else {
            mView.successful(user);
        }
    }
}
