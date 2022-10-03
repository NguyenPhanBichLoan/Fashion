package com.fashion.firebase.dlfashion.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fashion.firebase.dlfashion.FilterCategoryActivity;
import com.fashion.firebase.dlfashion.R;
import com.fashion.firebase.dlfashion.data.model.Category;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);

        if (category == null) {
            return;
        }

        holder.name.setText(category.getName());
        byte[] decodedString = Base64.decode(category.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.image.setImageBitmap(decodedByte);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FilterCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("categoryID", category.getId());
                bundle.putString("categoryName", category.getName());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (categoryList != null) {
            return categoryList.size();
        }

        return 0;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private RoundedImageView image;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.label_category);
            image = itemView.findViewById(R.id.image_category);
        }
    }
}
