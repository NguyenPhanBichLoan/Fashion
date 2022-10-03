package com.fashion.firebase.dlfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.model.Item;
import com.fashion.firebase.dlfashion.data.model.Product;
import com.fashion.firebase.dlfashion.ui.adapter.CartAdapter;
import com.fashion.firebase.dlfashion.ui.constract.CartConstract;
import com.fashion.firebase.dlfashion.ui.constract.CartPresenter;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartConstract.IView {

    private CartConstract.IPresenter mPresenter;
    private CartAdapter cartAdapter;
    private RecyclerView rcvCart;
    private TextView totalCart;
    private Button btnCheckOut;
    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cart);

        initGUI();
        initData();

        cartAdapter.setActionListener(new CartAdapter.ProductItemActionListener() {
            @Override
            public void resetTotal() {
                total = 0;
                getTotal();
            }
        });
//        btnCheckOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CartActivity.this, CheckOut.class);
//                Bundle bundle = new Bundle();
//                bundle.putDouble("price", total);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    private void initGUI() {
        btnCheckOut = findViewById(R.id.btn_check_out);
        totalCart = findViewById(R.id.tv_cart_total);
        rcvCart = findViewById(R.id.rcv_cart);
    }

    private void initData() {
        mPresenter = new CartPresenter(this);
        mPresenter.setView(this);
        mPresenter.getItemList();
    }

    @Override
    public void setItemListToView(List<Item> items) {
        cartAdapter = new CartAdapter(this, items);
        rcvCart.setHasFixedSize(true);
        rcvCart.setLayoutManager(new LinearLayoutManager(this));
        rcvCart.setAdapter(cartAdapter);
        getTotal();
    }

    private void getTotal() {
        List<Item> itemList = DatabaseDao.getInstance().getCartDao().all();
        for (Item item : itemList) {
            Product product = DatabaseDao.getInstance().getProductDao().find(item.getProduct());
            total = total + (product.getPrice() * item.getQuantity());
        }
        totalCart.setText("$" + Math.ceil((total * 100) / 100));
    }
}