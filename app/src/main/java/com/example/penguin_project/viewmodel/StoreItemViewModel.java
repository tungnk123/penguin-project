package com.example.penguin_project.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.StoreItem;

import java.util.List;

public class StoreItemViewModel extends AndroidViewModel {
    private HabitDataBase habitDataBase;
    private LiveData<List<StoreItem>> treesList;
    private LiveData<List<StoreItem>> musicsList;
    private LiveData<List<StoreItem>> themesList;
    private LiveData<List<StoreItem>> specialItemsList;

    public StoreItemViewModel(Application application) {
        super(application);
        habitDataBase = HabitDataBase.getInstance(application);
        treesList = habitDataBase.habitDAO().getStoreItemByType("tree");
        musicsList = habitDataBase.habitDAO().getStoreItemByType("music");
        themesList = habitDataBase.habitDAO().getStoreItemByType("theme");
        specialItemsList = habitDataBase.habitDAO().getStoreItemByType("special item");
    }

    public LiveData<List<StoreItem>> getTreesList() {
        return treesList;
    }
    public LiveData<List<StoreItem>> getMusicsList() {
        return musicsList;
    }
    public LiveData<List<StoreItem>> getThemesList() {
        return themesList;
    }
    public LiveData<List<StoreItem>> getSpecialItemsList() {
        return specialItemsList;
    }

    public void insertStoreItem(StoreItem storeItem) {
        habitDataBase.habitDAO().insertStoreItem(storeItem);
    }

    public void deleteStoreItem(int storeItemId) {
        habitDataBase.habitDAO().deleteItem(storeItemId);
    }
    public void updateItemImg(int item_id,int img) {
        habitDataBase.habitDAO().updateItemImg(item_id, img);
    }
    public void updateItemName(int item_id,String name) {
        habitDataBase.habitDAO().updateItemName(item_id, name);
    }
    public void updateItemPrice(int item_id,int price) {
        habitDataBase.habitDAO().updateItemPrice(item_id, price);
    }
    public void updateItemPurchased(int item_id,boolean isPurchased) {
        habitDataBase.habitDAO().updateIsPurchasedById(item_id, isPurchased);
    }

}
