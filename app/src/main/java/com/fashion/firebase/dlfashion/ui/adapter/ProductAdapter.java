package com.fashion.firebase.dlfashion.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fashion.firebase.dlfashion.ProductDetailActivity;
import com.fashion.firebase.dlfashion.R;
import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        if (product == null) {
            return;
        }

        holder.name.setText(product.getName());
        holder.price.setText("$" + product.getPrice());
        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.image.setImageBitmap(decodedByte);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", product.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
                // Luong Thanh Dat
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }

        return 0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView name, price;
        private ImageView image;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_product);
            price = itemView.findViewById(R.id.price_product);
            image = itemView.findViewById(R.id.image_product);
        }
    }
}
