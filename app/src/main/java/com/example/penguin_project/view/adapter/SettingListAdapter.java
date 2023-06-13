package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.SettingItem;

import java.util.List;

public class SettingListAdapter extends RecyclerView.Adapter<SettingListAdapter.SettingViewHolder> {
    private Context context;
    private List<SettingItem> settingItemList;

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SettingListAdapter(Context context, List<SettingItem> settingItemList) {
        this.context = context;
        this.settingItemList = settingItemList;
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.setting_item, parent, false);
        return new SettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        SettingItem settingItem = settingItemList.get(position);
        holder.bind(settingItem);
    }

    @Override
    public int getItemCount() {
        if (settingItemList == null) return 0;
        return settingItemList.size();
    }

    public class SettingViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView tvStatus;

        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_settingsItem_name);
            tvStatus = itemView.findViewById(R.id.tv_settingItem_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            SettingItem item = settingItemList.get(position);
                            listener.onItemClick(item);
                        }
                    }
                }
            });
        }

        public void bind(SettingItem settingItem) {
            titleTextView.setText(settingItem.getTitle());
            tvStatus.setText(settingItem.getStatus());
            Drawable drawableStart = ContextCompat.getDrawable(itemView.getContext(), settingItem.getIcon());
            titleTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableStart, null, null, null);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SettingItem item);
    }
}
