package com.fashion.firebase.dlfashion.ui.constract;

import com.fashion.firebase.dlfashion.data.model.User;

public interface LoginConstract {

    interface IView{
        void failure();
        void successful(User user);
    }

    interface IPresenter{
        void setView(LoginConstract.IView view);
        void checkLogin(String username, String password);
    }
}
