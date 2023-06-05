package com.example.penguin_project.view.adapter;

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
            holder.tvCoinNumber.setText(storeItem.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return storeItemList.size();
    }

    public class StoreItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCoinNumber;
        ImageView imgIcon;
        LinearLayout llCoinWrapper;

        StoreItemViewHolder (View itemView) {
            super(itemView);
            tvCoinNumber = itemView.findViewById(R.id.tv_storeItem_coin);
            imgIcon = itemView.findViewById(R.id.img_storeItem_icon);
            llCoinWrapper = itemView.findViewById(R.id.ll_storeItem_iconWrapper);

            llCoinWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO xu ly khi nhan buy, hien dialog thong bao
                    Toast.makeText(itemView.getContext(), "Buy click", Toast.LENGTH_LONG);
                }
            });

        }
    }
}