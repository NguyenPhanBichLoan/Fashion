package com.fashion.firebase.dlfashion.ui.constract;

import com.fashion.firebase.dlfashion.data.model.Category;
import com.fashion.firebase.dlfashion.data.model.Product;
import com.fashion.firebase.dlfashion.data.model.User;

import java.util.List;

public interface HomeConstract {

    interface IView{
        void setCategoryListToView(List<Category> categoryList);
        void setProductListToView(List<Product> productList);
    }

    interface IPresenter{
        void setView(HomeConstract.IView view);
        void getCategoryListTop8();
        void getProductListTop8();
    }
}
