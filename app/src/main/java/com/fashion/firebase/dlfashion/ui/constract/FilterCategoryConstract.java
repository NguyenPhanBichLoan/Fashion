package com.fashion.firebase.dlfashion.ui.constract;

import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public interface FilterCategoryConstract {

    interface IView{
        void setProductListByCategoryToView(List<Product> productList);
    }

    interface IPresenter{
        void setView(IView view);
        void getProductListByCategory(int id);
    }
}
