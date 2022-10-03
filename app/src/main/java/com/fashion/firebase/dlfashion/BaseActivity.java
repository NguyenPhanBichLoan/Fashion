package com.fashion.firebase.dlfashion;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.DatabaseSQLite;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DatabaseDao.init(new DatabaseSQLite(this));
    }
}
