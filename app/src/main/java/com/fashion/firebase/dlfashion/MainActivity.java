package com.fashion.firebase.dlfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.fashion.firebase.dlfashion.data.local.DataLocalManager;
import com.fashion.firebase.dlfashion.data.model.Category;
import com.fashion.firebase.dlfashion.data.model.Product;
import com.fashion.firebase.dlfashion.databinding.ActivityMainBinding;
import com.fashion.firebase.dlfashion.ui.adapter.CategoryAdapter;
import com.fashion.firebase.dlfashion.ui.adapter.ProductAdapter;
import com.fashion.firebase.dlfashion.ui.constract.HomeConstract;
import com.fashion.firebase.dlfashion.ui.constract.HomePresenter;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeConstract.IView {

    private ImageCarousel carousel;
    private HomeConstract.IPresenter mPresenter;
    private RecyclerView rcvCategory, rcvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initGUI();
        initSlider();
        initData();
    }

    private void initGUI() {
        carousel = findViewById(R.id.carousel);
        rcvCategory = findViewById(R.id.categoriesList_home);
        rcvProduct = findViewById(R.id.productsList_home);
    }

    private void initSlider() {
        carousel.addData(new CarouselItem("https://bizweb.sapocdn.net/100/438/408/themes/878697/assets/slider_1.jpg?1664559603705", ""));
        carousel.addData(new CarouselItem("https://bizweb.sapocdn.net/100/438/408/themes/878697/assets/slider_2.jpg?1664559603705", ""));
        carousel.addData(new CarouselItem("https://bizweb.sapocdn.net/100/438/408/themes/878697/assets/slider_3.jpg?1664559603705", ""));
        carousel.addData(new CarouselItem("https://bizweb.sapocdn.net/100/438/408/themes/878697/assets/slider_4.jpg?1664559603705", ""));
        carousel.addData(new CarouselItem("https://bizweb.sapocdn.net/100/438/408/themes/878697/assets/slider_5.jpg?1664559603705", ""));
    }

    private void initData() {
        mPresenter = new HomePresenter(this);
        mPresenter.setView(this);
        mPresenter.getCategoryListTop8();
        mPresenter.getProductListTop8();
    }

    @Override
    public void setCategoryListToView(List<Category> categoryList) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);
        categoryAdapter.setData(categoryList);
        rcvCategory.setLayoutManager(new GridLayoutManager(this, 4));
        rcvCategory.setAdapter(categoryAdapter);
        rcvCategory.setHasFixedSize(true);
    }

    @Override
    public void setProductListToView(List<Product> productList) {
        ProductAdapter productAdapter = new ProductAdapter(this);
        productAdapter.setData(productList);
        rcvProduct.setLayoutManager(new GridLayoutManager(this, 3));
        rcvProduct.setAdapter(productAdapter);
        rcvProduct.setHasFixedSize(true);
    }
}