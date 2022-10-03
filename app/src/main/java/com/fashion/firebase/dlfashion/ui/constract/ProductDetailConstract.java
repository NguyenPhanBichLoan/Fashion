package com.fashion.firebase.dlfashion.ui.constract;

import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public interface ProductDetailConstract {

    interface IView{
        void setProductToView(Product product);
    }

    interface IPresenter{
        void setView(ProductDetailConstract.IView view);
        Product getProduct(int id);
        void addToCart(int id, int quantity);
    }
}
