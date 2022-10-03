package com.fashion.firebase.dlfashion.ui.constract;

import com.fashion.firebase.dlfashion.data.model.Item;

import java.util.List;

public interface CartConstract {

    interface IView{
        void setItemListToView(List<Item> items);
    }

    interface IPresenter{
        void setView(IView view);
        void getItemList();
    }
}
