package com.example.penguin_project.view.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Habits;

import java.util.List;

public class HabitItemAdapter extends RecyclerView.Adapter<HabitItemAdapter.ItemViewHolder> {

    private List<Habits> itemList;

    public HabitItemAdapter(List<Habits> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Habits itemName = itemList.get(position);
//        holder.itemColor.setBackgroundColor(itemName.getColor());
        holder.item_Title.setText(itemName.getTitle());
        holder.itemIcon.setImageResource(R.mipmap.icon_water);
        holder.item_CurrentProgress.setText("0");
        holder.item_timePerDay.setText("5");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void removeItem(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    public void checkDoneItem(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout itemColor;
        public ImageView itemIcon;
        public TextView item_Title;
        public TextView item_CurrentProgress;
        public TextView item_timePerDay;
        public Button buttonDelete;
        public Button buttonCheckDone;

        public ItemViewHolder(View itemView) {
            super(itemView);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonCheckDone = itemView.findViewById(R.id.buttonCheckDone);
            itemColor = itemView.findViewById(R.id.habitItem_Color);
            itemIcon = itemView.findViewById(R.id.habitItem_Icon);
            item_Title = itemView.findViewById(R.id.habitItem_Title);
            item_CurrentProgress = itemView.findViewById(R.id.habitItem_CurrentProgress);
            item_timePerDay = itemView.findViewById(R.id.habitItem_TimePerDay);


            // Set different icons and colors for buttons
            Drawable deleteIcon = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_launcher_background);
            Drawable checkDoneIcon = ContextCompat.getDrawable(itemView.getContext(), R.drawable.baseline_done_24);
            int deleteColor = ContextCompat.getColor(itemView.getContext(), R.color.purple_200);
            int checkDoneColor = ContextCompat.getColor(itemView.getContext(), R.color.teal_200);

            buttonDelete.setCompoundDrawablesWithIntrinsicBounds(deleteIcon, null, null, null);
            buttonCheckDone.setCompoundDrawablesWithIntrinsicBounds(checkDoneIcon, null, null, null);
            buttonDelete.setBackgroundColor(deleteColor);
            buttonCheckDone.setBackgroundColor(checkDoneColor);
        }
    }
}
