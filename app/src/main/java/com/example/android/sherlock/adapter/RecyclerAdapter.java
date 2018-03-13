package com.example.android.sherlock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sherlock.R;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by stephen on 3/7/18.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.SherlockViewHolder> {
    public interface CartListener {
        void onItemAddToCart();
    }

    private List<Item> itemData;
    private Map<Long, Store> storeMap;
    private Context c;
    private NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private Map<Long, Double> distanceMap;
    private List<Item> cart;
    private CartListener listener;
    static class SherlockViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nameText;
        TextView descText;
        TextView priceText;
        TextView distanceText;
        TextView storeText;
        ImageView image;
        ImageButton cartButton;

        SherlockViewHolder(View itemView ) {
            super(itemView);
            cv =  itemView.findViewById(R.id.product_card);
            nameText =  itemView.findViewById(R.id.card_productName);
            descText = itemView.findViewById(R.id.card_productDescription);
            priceText = itemView.findViewById(R.id.card_productPrice);
            distanceText= itemView.findViewById(R.id.card_productDistance);
            storeText = itemView.findViewById(R.id.card_productStore);
            image = itemView.findViewById(R.id.card_productImage);
            cartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }

    public RecyclerAdapter(Context c, List<Item> itemData, Map<Long, Store> storeMap, Comparator<Item> comparator,
                           Map<Long, Double> distanceMap, List<Item> cart) {
        this.itemData = itemData;
        Collections.sort(this.itemData, comparator);
        this.distanceMap = distanceMap;
        this.storeMap = storeMap;
        this.c = c;
        if(cart != null) {
            this.cart = cart;
        }else {
            this.cart = new ArrayList<>();
        }
        this.listener = (CartListener) c;
    }

    public List<Item> getCartContents() {
        return this.cart;
    }

    @NonNull
    @Override
    public SherlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new SherlockViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SherlockViewHolder holder, int position) {
        Item i = itemData.get(position);
        Store s = storeMap.get(i.getStoreId());
        holder.nameText.setText(i.getName());
        holder.descText.setText(i.getDescription());
        holder.priceText.setText(formatter.format(i.getPrice()));
        if(s != null){
            holder.storeText.setText(s.getStoreName());
            holder.distanceText.setText(String.format(Locale.US, "%.2f mi", distanceMap.get(s.getId())));
        }
        Picasso.with(c).load(i.getImageUrl()).into(holder.image);
        holder.cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToCart(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemData.size();
    }

    private void addItemToCart(int position) {
        this.cart.add(itemData.get(position));
        this.listener.onItemAddToCart();
    }
}