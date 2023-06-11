package com.example.penguin_project.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Tree;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private List<Tree> plantList;
    public static int selectedPosition = RecyclerView.NO_POSITION;

    public PlantAdapter(List<Tree> plantList) {
        this.plantList = plantList;
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlantViewHolder holder, int position) {
        Tree plant = plantList.get(position);
        holder.tvPlantName.setText(plant.getTitle());
        holder.imgPlantIcon.setImageResource(plant.getIcon());
        holder.updateCardViewAppearance(position == selectedPosition);
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPlantName;
        ImageView imgPlantIcon;

        PlantViewHolder(View itemView) {
            super(itemView);
            tvPlantName = itemView.findViewById(R.id.tv_plantItem_plantName);
            imgPlantIcon = itemView.findViewById(R.id.img_plantItem_plantIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (selectedPosition != RecyclerView.NO_POSITION) {
                    // Deselect the previously selected item
                    notifyItemChanged(selectedPosition);
                }
                selectedPosition = position;
                // Select the clicked item
                notifyItemChanged(selectedPosition);

                Tree plant = plantList.get(position);
                Toast.makeText(view.getContext(), plant.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }

        private void updateCardViewAppearance(boolean isSelected) {
            if (isSelected) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.lime_energy));
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.black_title));
            }
        }
    }
}
