package com.fashion.firebase.dlfashion.ui.constract;

import android.content.Context;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.DatabaseSQLite;
import com.fashion.firebase.dlfashion.data.model.Item;

import java.util.List;

public class CartPresenter implements CartConstract.IPresenter {

    private CartConstract.IView mView;

    public CartPresenter(Context context){
        DatabaseDao.init((new DatabaseSQLite(context)));
    }

    @Override
    public void setView(CartConstract.IView view) {
        this.mView = view;
    }

    @Override
    public void getItemList() {
        List<Item> items = DatabaseDao.getInstance().getCartDao().all();
        mView.setItemListToView(items);
    }
}
