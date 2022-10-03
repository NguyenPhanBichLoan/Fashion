package com.fashion.firebase.dlfashion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.local.DataLocalManager;
import com.fashion.firebase.dlfashion.data.model.User;
import com.fashion.firebase.dlfashion.ui.constract.LoginConstract;
import com.fashion.firebase.dlfashion.ui.constract.LoginPresenter;
import com.fashion.firebase.dlfashion.utils.CustomDialog;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements LoginConstract.IView {

    private LoginConstract.IPresenter mPresenter;
    private TextInputEditText edtUsername, edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initGUI();
        initData();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                mPresenter.checkLogin(username, password);
            }
        });
    }

    private void initGUI() {
        edtUsername = findViewById(R.id.edt_username_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void initData() {
        mPresenter = new LoginPresenter(this);
        mPresenter.setView(this);
    }

    @Override
    public void failure() {
        CustomDialog dialog = new CustomDialog(this);
        dialog.initDialog("Failure", getResources().getColor(R.color.red_dark));
    }

    @Override
    public void successful(User user) {
        CustomDialog dialog = new CustomDialog(this);
        dialog.initDialog("Successful", getResources().getColor(R.color.green_dark));
        DataLocalManager dataLocalManager = new DataLocalManager(this);
        dataLocalManager.putBooleanValue("login", true);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, 1000);
    }
}