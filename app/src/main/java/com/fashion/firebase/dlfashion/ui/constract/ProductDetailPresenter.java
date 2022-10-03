package com.fashion.firebase.dlfashion.ui.constract;

import android.content.Context;
import android.util.Log;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.DatabaseSQLite;
import com.fashion.firebase.dlfashion.data.model.Item;
import com.fashion.firebase.dlfashion.data.model.Product;

public class ProductDetailPresenter implements ProductDetailConstract.IPresenter {

    private ProductDetailConstract.IView mView;

    public ProductDetailPresenter(Context context) {
        DatabaseDao.init((new DatabaseSQLite(context)));
    }

    @Override
    public void setView(ProductDetailConstract.IView view) {
        this.mView = view;
    }

    @Override
    public Product getProduct(int id) {
        Product product = DatabaseDao.getInstance().getProductDao().find(id);
        mView.setProductToView(product);
        return product;
    }

    @Override
    public void addToCart(int id, int quantity) {
        Item item = DatabaseDao.getInstance().getCartDao().find(id);

        Item newItem = new Item(id, quantity);

        if (item.getProduct() == id) {
            item.setQuantity(item.getQuantity() + quantity);
            DatabaseDao.getInstance().getCartDao().update(item);
            return;
        }

        DatabaseDao.getInstance().getCartDao().insert(newItem);
    }
}
