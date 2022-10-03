package com.fashion.firebase.dlfashion.ui.constract;

import android.content.Context;
import android.util.Log;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.DatabaseSQLite;
import com.fashion.firebase.dlfashion.data.model.Category;
import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public class HomePresenter implements HomeConstract.IPresenter {

    private HomeConstract.IView mView;

    public HomePresenter(Context context) {
        DatabaseDao.init(new DatabaseSQLite(context));
    }

    @Override
    public void setView(HomeConstract.IView view) {
        this.mView = view;
    }

    @Override
    public void getCategoryListTop8() {
        List<Category> categoryList = DatabaseDao.getInstance().getCategoryDao().all();
        mView.setCategoryListToView(categoryList);
    }

    @Override
    public void getProductListTop8() {
        List<Product> productList = DatabaseDao.getInstance().getProductDao().findByTop8View();
        mView.setProductListToView(productList);
    }
}
