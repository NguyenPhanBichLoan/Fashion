package com.fashion.firebase.dlfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fashion.firebase.dlfashion.data.model.Product;
import com.fashion.firebase.dlfashion.ui.constract.ProductDetailConstract;
import com.fashion.firebase.dlfashion.ui.constract.ProductDetailPresenter;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, ProductDetailConstract.IView {

    private Product product;
    private TextView name, description, price;
    private ImageView image, imageCopy;
    private Button btnRemoveQuantity, btnAddQuantity, btnBuyNow, btnAddToCart;
    private AppCompatImageView btnBack;
    private TextView tvQuantity;
    private int id, quantity;
    private ScrollView scrollView;
    private ProductDetailConstract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_detail);

        initGUI();
        initData();
        initScrollView();
    }

    private void initGUI() {
        name = findViewById(R.id.tv_product_name);
        price = findViewById(R.id.tv_product_price);
        description = findViewById(R.id.tv_product_description);
        image = findViewById(R.id.iv_product_image);
        imageCopy = findViewById(R.id.iv_product_image_copy);
        scrollView = findViewById(R.id.sv_product_detail);
        btnRemoveQuantity = findViewById(R.id.btn_remove_quantity);
        tvQuantity = findViewById(R.id.tv_product_quantity);
        btnAddQuantity = findViewById(R.id.btn_add_quantity);
        btnBuyNow = findViewById(R.id.btn_buy_now);
        btnBack = findViewById(R.id.back_home);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);

        btnRemoveQuantity.setOnClickListener(this::onClick);
        btnBack.setOnClickListener(this::onClick);
        btnAddQuantity.setOnClickListener(this::onClick);
        btnAddToCart.setOnClickListener(this::onClick);
        quantity = Integer.parseInt(tvQuantity.getText().toString());
    }

    private void initData() {
        mPresenter = new ProductDetailPresenter(this);
        mPresenter.setView(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        id = bundle.getInt("id");
        product = mPresenter.getProduct(id);
    }

    private void initScrollView() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && description.length() > 0) {
                    btnBuyNow.setVisibility(View.GONE);
                    btnAddToCart.setVisibility(View.GONE);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP && description.length() > 0) {
                    btnBuyNow.setVisibility(View.VISIBLE);
                    btnAddToCart.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_remove_quantity:
                if (quantity == 1) {
                    return;
                }
                --quantity;
                tvQuantity.setText(String.valueOf(quantity));
                Log.i("quantity", String.valueOf(quantity));
                break;
            case R.id.btn_add_quantity:
                ++quantity;
                tvQuantity.setText(String.valueOf(quantity));
                Log.i("quantity", String.valueOf(quantity));
                break;
            case R.id.btn_add_to_cart:
                mPresenter.addToCart(id, quantity);
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.back_home:
                Intent back = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(back);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    @Override
    public void setProductToView(Product product) {
        name.setText(product.getName());
        description.setText(product.getDescription());
        price.setText("$" + product.getPrice());
        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        image.setImageBitmap(decodedByte);
        imageCopy.setImageBitmap(decodedByte);
    }
}