package com.fashion.firebase.dlfashion.ui.adapter;

import static android.view.View.GONE;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fashion.firebase.dlfashion.ProductDetailActivity;
import com.fashion.firebase.dlfashion.R;
import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.FilterCategoryViewHolder> {

    private Context context;
    private ProductItemActionListener actionListener;
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setActionListener(ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public ImageView onItemTap(ImageView imageView) {
        return imageView;
    }

    public FilterCategoryAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilterCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_2, parent, false);
        return new FilterCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterCategoryViewHolder holder, int position) {
        Product product = productList.get(position);

        if (product == null) {
            return;
        }

        holder.name.setText(product.getName());
        holder.price.setText("$" + (Math.round(product.getPrice() * 100) / 100));
        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.image.setImageBitmap(decodedByte);
        holder.imageCopy.setImageBitmap(decodedByte);
        holder.down.setVisibility(GONE);
        holder.up.setVisibility(GONE);
        holder.delete.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24);
        holder.quantity.setVisibility(GONE);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", product.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionListener!=null)
                    actionListener.onItemTap(holder.imageCopy);
                holder.imageCopy.setVisibility(View.VISIBLE);
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

    public static class FilterCategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView name, price, quantity;
        private ImageView image, imageCopy, delete;
        private ImageButton down, up;

        public FilterCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.eachCartItemName);
            price = itemView.findViewById(R.id.eachCartItemPriceTv);
            quantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            image = itemView.findViewById(R.id.eachCartItemIV);
            imageCopy = itemView.findViewById(R.id.eachCartItemIVCopy);
            delete = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            down = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
            up = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
        }
    }

    public interface ProductItemActionListener{
        void onItemTap(ImageView imageView);
    }
}
