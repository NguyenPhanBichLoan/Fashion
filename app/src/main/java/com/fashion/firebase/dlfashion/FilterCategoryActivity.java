package com.fashion.firebase.dlfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.firebase.dlfashion.data.model.Product;
import com.fashion.firebase.dlfashion.ui.adapter.FilterCategoryAdapter;
import com.fashion.firebase.dlfashion.ui.constract.FilterCategoryConstract;
import com.fashion.firebase.dlfashion.ui.constract.FilterCategoryPresenter;
import com.fashion.firebase.dlfashion.utils.CircleAnimationUtil;

import java.util.List;

public class FilterCategoryActivity extends AppCompatActivity implements FilterCategoryConstract.IView {

    private FilterCategoryConstract.IPresenter mPresenter;
    private TextView tvCategoryName;
    private RecyclerView rcvFilterCategory;
    private FilterCategoryAdapter filterCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_filter_category);

        initGUI();
        initData();
        filterCategoryAdapter.setActionListener(new FilterCategoryAdapter.ProductItemActionListener() {
            @Override
            public void onItemTap(ImageView imageView) {
                if (imageView != null)
                    makeFlyAnimation(imageView);
            }
        });
    }

    private void initGUI() {
        tvCategoryName = findViewById(R.id.tv_filterCategory_name);
        rcvFilterCategory = findViewById(R.id.rcv_product_byCategory);
    }

    private void initData() {
        mPresenter = new FilterCategoryPresenter(this);
        mPresenter.setView(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            return;
        }

        tvCategoryName.setText(bundle.getString("categoryName"));
        mPresenter.getProductListByCategory(bundle.getInt("categoryID"));
    }

    private void makeFlyAnimation(ImageView targetView) {

        RelativeLayout destView = (RelativeLayout) findViewById(R.id.cart_product_detail);

        new CircleAnimationUtil().attachActivity(this).setTargetView(targetView).setMoveDuration(1000).setDestView(destView).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(FilterCategoryActivity.this, "Continue Shopping...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).startAnimation();


    }

    @Override
    public void setProductListByCategoryToView(List<Product> productList) {
        filterCategoryAdapter = new FilterCategoryAdapter(this, productList);
        rcvFilterCategory.setLayoutManager(new LinearLayoutManager(this));
        rcvFilterCategory.setAdapter(filterCategoryAdapter);
        rcvFilterCategory.setHasFixedSize(true);
    }
}