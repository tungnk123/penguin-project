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

    private String StoreItemType;

    public StoreItem(String itemName, int itemPrice, int itemImg, String description, String storeItemType, boolean isPurchased) {
        ItemName = itemName;
        ItemPrice = itemPrice;
        ItemImg = itemImg;
        Description = description;
        StoreItemType = storeItemType;
        IsPurchased = isPurchased;
    }

    private boolean IsPurchased;

    public String getStoreItemType() {
        return StoreItemType;
    }

    public void setStoreItemType(String storeItemType) {
        StoreItemType = storeItemType;
    }

    public int getItem_id() {
        return Item_id;
    }

    public void setItem_id(int Item_id) {
        this.Item_id = Item_id;
    }

    public boolean getIsPurchased() {
        return IsPurchased;
    }

    public void setIsPurchased(boolean IsPurchased) {
        this.IsPurchased = IsPurchased;
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

    public StoreItem(String itemName, int itemPrice, int itemImg, String description) {
        ItemName = itemName;
        ItemPrice = itemPrice;
        ItemImg = itemImg;
        Description = description;
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
