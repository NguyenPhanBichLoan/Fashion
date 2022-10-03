package com.fashion.firebase.dlfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private ConstraintLayout layoutSplashScreen;
    private ImageView ivLogo;
    private TextView tvLabel, tvSlogan;
    private Animation topAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        initGUI();
        initAnim();
    }

    private void initGUI() {
        layoutSplashScreen = findViewById(R.id.layout_splash_screen);
        ivLogo = findViewById(R.id.tv_logo);
        tvLabel = findViewById(R.id.tv_label);
        tvSlogan = findViewById(R.id.tv_slogan);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
    }

    private void initAnim() {
        ivLogo.setAnimation(topAnim);
        tvLabel.setAnimation(bottomAnim);
        tvSlogan.setAnimation(bottomAnim);

        layoutSplashScreen.setAlpha(1f);

        new Handler().postDelayed((Runnable) () -> {
            finish();
        }, 5000);

        layoutSplashScreen.animate().setDuration(4000).alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}