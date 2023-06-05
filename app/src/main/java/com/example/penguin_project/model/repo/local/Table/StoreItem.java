package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StoreItem")
public class StoreItem {
    @PrimaryKey(autoGenerate = true)
    private int Item_id;
    private String ItemName;
    private int ItemPrice;
    private int ItemImg;
    private String Description;

    public int getItem_id() {
        return Item_id;
    }

    public void setItem_id(int Item_id) {
        this.Item_id = Item_id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public int getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(int ItemPrice) {
        this.ItemPrice = ItemPrice;
    }

    public int getItemImg() {
        return ItemImg;
    }

    public void setItemImg(int ItemImg) {
        this.ItemImg = ItemImg;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public StoreItem(int Item_id, String ItemName, int ItemPrice, int ItemImg, String Description) {
        this.Item_id = Item_id;
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.ItemImg = ItemImg;
        this.Description = Description;
    }



}
