package com.fashion.firebase.dlfashion.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fashion.firebase.dlfashion.R;
import com.fashion.firebase.dlfashion.data.DatabaseDao;
import com.fashion.firebase.dlfashion.data.model.Item;
import com.fashion.firebase.dlfashion.data.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Item> itemList;
    private ProductItemActionListener actionListener;

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public CartAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_2, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Item item = itemList.get(position);

        if (item == null) {
            return;
        }

        Product product = DatabaseDao.getInstance().getProductDao().find(item.getProduct());
        holder.name.setText(product.getName());
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        holder.price.setText("$" + product.getPrice());
        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.image.setImageBitmap(decodedByte);
        holder.imageCopy.setVisibility(View.GONE);
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getQuantity() == 1) {
                    return;
                }
                item.setQuantity(item.getQuantity() - 1);
                DatabaseDao.getInstance().getCartDao().update(item);
                holder.quantity.setText(String.valueOf(item.getQuantity()));
                if (actionListener != null) {
                    actionListener.resetTotal();
                }
            }
        });
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setQuantity(item.getQuantity() + 1);
                DatabaseDao.getInstance().getCartDao().update(item);
                holder.quantity.setText(String.valueOf(item.getQuantity()));
                if (actionListener != null) {
                    actionListener.resetTotal();
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseDao.getInstance().getCartDao().delete(product.getId());
                setItemList(DatabaseDao.getInstance().getCartDao().all());
                if (actionListener != null) {
                    actionListener.resetTotal();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        }

        return 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private TextView name, price, quantity;
        private ImageView image, delete, imageCopy;
        private ImageButton down, up;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.eachCartItemName);
            price = itemView.findViewById(R.id.eachCartItemPriceTv);
            quantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            image = itemView.findViewById(R.id.eachCartItemIV);
            down = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
            up = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            delete = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            imageCopy = itemView.findViewById(R.id.eachCartItemIVCopy);
        }
    }

    public void setActionListener(CartAdapter.ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public interface ProductItemActionListener {
        void resetTotal();
    }
}
