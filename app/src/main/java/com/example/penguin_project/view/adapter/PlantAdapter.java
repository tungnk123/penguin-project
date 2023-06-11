package com.example.penguin_project.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Tree;
import com.example.penguin_project.view.fragment.StoreFragment;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    public List<Tree> plantList;
    private final StoreFragment storeFragment;

    public PlantAdapter(StoreFragment storeFragment, List<Tree> plantList) {
        this.storeFragment = storeFragment;
        this.plantList = plantList;
    }

    @NonNull
    @Override
    public PlantAdapter.PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item, parent, false);
        return new PlantAdapter.PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.PlantViewHolder holder, int position) {
        Tree plant = plantList.get(position);
        holder.tvPlantName.setText(plant.getTitle());
        holder.imgPlantIcon.setImageResource(plant.getIcon());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlantName;
        ImageView imgPlantIcon;

        PlantViewHolder(View itemView) {
            super(itemView);
            tvPlantName = itemView.findViewById(R.id.tv_plantItem_plantName);
            imgPlantIcon = itemView.findViewById(R.id.img_plantItem_plantIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tree plant = plantList.get(getAdapterPosition());
                    Toast.makeText(view.getContext(), plant.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
//            tvPlantName.setText();
        }
    }
}
