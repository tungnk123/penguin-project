package com.example.penguin_project.view.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.view.fragment.StoreFragment;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.StoreItemViewHolder>{

    private final List<StoreItem> storeItemList;

    private final StoreFragment storeFragment;


    public StoreItemAdapter(List<StoreItem> storeItemList, StoreFragment storeFragment) {
        this.storeItemList = storeItemList;
        this.storeFragment = storeFragment;
    }

    @NonNull
    @Override
    public StoreItemAdapter.StoreItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
            return new StoreItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreItemAdapter.StoreItemViewHolder holder, int position) {
            StoreItem storeItem = storeItemList.get(position);
            holder.imgIcon.setImageResource(storeItem.getItemImg());
            holder.tvItemName.setText(storeItem.getItemName());
            holder.tvCoinNumber.setText(String.valueOf(storeItem.getItemPrice()));
    }

    @Override
    public int getItemCount() {
        if (storeItemList == null) {
            return 0;
        }
        return storeItemList.size();
    }

    public class StoreItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCoinNumber;
        ImageView imgIcon;
        TextView tvItemName;
        LinearLayout llCoinWrapper;

        ImageView imgCoin;

        StoreItemViewHolder (View itemView) {
            super(itemView);
            tvCoinNumber = itemView.findViewById(R.id.tv_storeItem_coin);
            imgIcon = itemView.findViewById(R.id.img_storeItem_icon);
            llCoinWrapper = itemView.findViewById(R.id.ll_storeItem_iconWrapper);
            tvItemName = itemView.findViewById(R.id.tv_storeItem_itemName);
            imgCoin = itemView.findViewById(R.id.img_storeItem_coin);

            if (!tvCoinNumber.getText().equals("Purchased")) {
                llCoinWrapper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder.setTitle("Confirm Buy")
                                .setMessage("Are you sure you want to buy this item?")
                                .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // TODO handle buy action
                                        Toast.makeText(itemView.getContext(), "Item bought!", Toast.LENGTH_SHORT).show();
                                        // Add your code here to perform the buy action
                                        tvCoinNumber.setText("Purchased");
                                        imgCoin.setVisibility(View.GONE);
                                        llCoinWrapper.setBackgroundColor(view.getContext().getResources().getColor(R.color.grey));

                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // Dialog dismissed, do nothing
                                    }
                                })
                                .show();
                    }
                });
            }


        }
    }
}