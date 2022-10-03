package com.fashion.firebase.dlfashion.ui.constract;

import android.content.Context;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.DatabaseSQLite;
import com.fashion.firebase.dlfashion.data.model.Item;
import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public class FilterCategoryPresenter implements FilterCategoryConstract.IPresenter {

    private FilterCategoryConstract.IView mView;

    public FilterCategoryPresenter(Context context) {
        DatabaseDao.init(new DatabaseSQLite(context));
    }

    @Override
    public void setView(FilterCategoryConstract.IView view) {
        this.mView = view;
    }

    @Override
    public void getProductListByCategory(int id) {
        List<Product> productList = DatabaseDao.getInstance().getProductDao().findByCategory(id);
        mView.setProductListByCategoryToView(productList);
    }

    @Override
    public void addToCart(int id) {
        Item item = DatabaseDao.getInstance().getCartDao().find(id);

        Item newItem = new Item(id, 1);

        if (item.getProduct() == id) {
            item.setQuantity(item.getQuantity() + 1);
            DatabaseDao.getInstance().getCartDao().update(item);
            return;
        }

        DatabaseDao.getInstance().getCartDao().insert(newItem);
    }
}
