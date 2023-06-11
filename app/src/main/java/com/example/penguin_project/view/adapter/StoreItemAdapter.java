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
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.view.fragment.StoreFragment;
import com.example.penguin_project.viewmodel.StoreItemViewModel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.StoreItemViewHolder> {

    private final List<StoreItem> storeItemList;

    private final StoreFragment storeFragment;
    private StoreItemViewModel storeItemViewModel;


    public StoreItemAdapter(List<StoreItem> storeItemList, StoreFragment storeFragment, StoreItemViewModel storeItemViewModel) {
        this.storeItemList = storeItemList;
        this.storeFragment = storeFragment;
        this.storeItemViewModel = storeItemViewModel;
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

        if (storeItem.getIsPurchased()) {
            holder.tvCoinNumber.setText("Purchased");
            holder.imgCoin.setVisibility(View.GONE);

        } else {
            holder.tvCoinNumber.setText(String.valueOf(storeItem.getItemPrice()));
            holder.imgCoin.setVisibility(View.VISIBLE);

        }
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

        StoreItemViewHolder(View itemView) {
            super(itemView);
            tvCoinNumber = itemView.findViewById(R.id.tv_storeItem_coin);
            imgIcon = itemView.findViewById(R.id.img_storeItem_icon);
            llCoinWrapper = itemView.findViewById(R.id.ll_storeItem_iconWrapper);
            tvItemName = itemView.findViewById(R.id.tv_storeItem_itemName);
            imgCoin = itemView.findViewById(R.id.img_storeItem_coin);

            llCoinWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("Confirm Buy")
                            .setMessage("Are you sure you want to buy this item?")
                            .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Get the clicked item
                                    StoreItem clickedItem = storeItemList.get(getAdapterPosition());

                                    if (!clickedItem.getIsPurchased()) {
                                        // Update the purchase status
                                        if (StoreFragment.coinSharedPreference.getInt("money", 100) >= clickedItem.getItemPrice()) {
                                            clickedItem.setIsPurchased(true);
                                            storeItemViewModel.updateItemPurchased(clickedItem.getItem_id(), true);
                                            // Update the UI
                                            tvCoinNumber.setText("Purchased");
                                            imgCoin.setVisibility(View.GONE);
                                            int newMoney = StoreFragment.coinSharedPreference.getInt("money", 100) - clickedItem.getItemPrice();
                                            StoreFragment.storeNewMoney(newMoney);
                                            Toast.makeText(itemView.getContext(), "Item bought!", Toast.LENGTH_SHORT).show();
                                            // neu mua item la tree se cap nhap trong db la plant da mua
                                            if (Objects.equals(clickedItem.getStoreItemType(), "tree")) {
                                                Toast.makeText(itemView.getContext(), clickedItem.getItemName() + " " + clickedItem.getItem_id(), Toast.LENGTH_LONG).show();
                                                HabitDataBase.getInstance(itemView.getContext()).habitDAO().updateTreeForestIsPurchased(clickedItem.getItem_id(), true);
                                                notifyDataSetChanged();
                                            }
                                        }
                                        else {
                                            Toast.makeText(itemView.getContext(), "Not enough money!     😅😅😅😅", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(itemView.getContext(), "Item is already purchased!", Toast.LENGTH_SHORT).show();
                                    }
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