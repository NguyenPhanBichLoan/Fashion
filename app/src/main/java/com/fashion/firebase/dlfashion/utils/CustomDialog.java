package com.fashion.firebase.dlfashion.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fashion.firebase.dlfashion.LoginActivity;
import com.fashion.firebase.dlfashion.R;

public class CustomDialog {

    private Context context;
    private TextView tvMessage;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public void initDialog(String message, int color) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        tvMessage = dialog.findViewById(R.id.tv_dialog_message);
        tvMessage.setText(message);
        tvMessage.setTextColor(color);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);
        dialog.show();
    }
}
