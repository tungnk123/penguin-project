package com.example.penguin_project.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.view.adapter.StoreItemAdapter;
import com.example.penguin_project.viewmodel.StoreItemViewModel;

import java.util.ArrayList;
import java.util.List;


public class StoreFragment extends Fragment {

    private RecyclerView rcvTreeItems;
    private RecyclerView rcvMusicItems;
    private RecyclerView rcvThemeItems;
    private RecyclerView rcvSpecialItems;

    private StoreItemAdapter storeItemAdapter;

    private StoreItemViewModel storeItemViewModel;

    private List<StoreItem> treeItemsList;
    private List<StoreItem> musicItemsList;
    private List<StoreItem> themeItemsList;
    private List<StoreItem> specialItemsList;

    private StoreItemAdapter treeAdapter;
    private StoreItemAdapter musicAdapter;
    private StoreItemAdapter themeAdapter;
    private StoreItemAdapter specialItemAdapter;

    public static SharedPreferences coinSharedPreference;
    public static TextView tvCoinNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        //region Set Shared Preferences for coin number
        coinSharedPreference = getContext().getSharedPreferences("coin_number", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = coinSharedPreference.edit();
//        editor.putInt("money", 100 );
//        editor.commit();
        tvCoinNumber = (TextView) view.findViewById(R.id.txt_ActionBar_coin);
        tvCoinNumber.setText(String.valueOf(coinSharedPreference.getInt("money", 1000)));
        //endregion
        treeItemsList = new ArrayList<>();
        musicItemsList = new ArrayList<>();
        themeItemsList = new ArrayList<>();
        specialItemsList = new ArrayList<>();
        //region Anh xa
        rcvTreeItems = view.findViewById(R.id.rcv_storeFragment_treeList);
        rcvThemeItems = view.findViewById(R.id.rcv_storeFragment_themeList);
        rcvMusicItems = view.findViewById(R.id.rcv_storeFragment_musicList);
        rcvSpecialItems = view.findViewById(R.id.rcv_storeFragment_specialItemsList);
        //endregion

        //region set ViewModel
        storeItemViewModel = new ViewModelProvider(this).get(StoreItemViewModel.class);


        //endregion

        // Create separate adapters for each section
        treeAdapter = new StoreItemAdapter(treeItemsList, this, storeItemViewModel);
        musicAdapter = new StoreItemAdapter(musicItemsList, this, storeItemViewModel);
        themeAdapter = new StoreItemAdapter(themeItemsList, this, storeItemViewModel);
        specialItemAdapter = new StoreItemAdapter(specialItemsList, this, storeItemViewModel);

        // Set adapters to corresponding RecyclerViews
        rcvTreeItems.setAdapter(treeAdapter);
        rcvThemeItems.setAdapter(themeAdapter);
        rcvMusicItems.setAdapter(musicAdapter);
        rcvSpecialItems.setAdapter(specialItemAdapter);

        storeItemViewModel.getTreesList().observe(getViewLifecycleOwner(), trees -> {
            treeItemsList.clear();
            treeItemsList.addAll(trees);
            treeAdapter.notifyDataSetChanged();
        });

        storeItemViewModel.getThemesList().observe(getViewLifecycleOwner(), themes -> {
            themeItemsList.clear();
            themeItemsList.addAll(themes);
            themeAdapter.notifyDataSetChanged();
        });

        storeItemViewModel.getMusicsList().observe(getViewLifecycleOwner(), musics -> {
            musicItemsList.clear();
            musicItemsList.addAll(musics);
            musicAdapter.notifyDataSetChanged();
        });

        storeItemViewModel.getSpecialItemsList().observe(getViewLifecycleOwner(), specialItems -> {
            specialItemsList.clear();
            specialItemsList.addAll(specialItems);
            specialItemAdapter.notifyDataSetChanged();
        });

        //endregion

        //region Set Layout Manager
        rcvTreeItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvMusicItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvThemeItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ;
        rcvSpecialItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //endregion


        //endregion
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Insert the store items into the database here
        storeItemViewModel = new ViewModelProvider(this).get(StoreItemViewModel.class);

        //region Generate Data

        StoreItem tree1 = new StoreItem("Maple", 10, R.mipmap.icon_storeitem_maple_tree, "Description", "tree", true);
        StoreItem tree2 = new StoreItem("Tulip", 20, R.mipmap.icon_storeiten_tulip, "Description", "tree", false);
        StoreItem tree3 = new StoreItem("Hickory", 10, R.mipmap.icon_storeitem_hickory, "Description", "tree", false);
        StoreItem tree4 = new StoreItem("Coconut", 50, R.mipmap.icon_storeitem_coconuttree, "Description", "tree", false);
        StoreItem tree5 = new StoreItem("Pine", 40, R.mipmap.icon_pine, "Description", "tree", false);
        StoreItem tree6 = new StoreItem("Bamboo", 25, R.mipmap.icon_bamboo, "Description", "tree", false);


        StoreItem theme1 = (new StoreItem("Pine", 120, R.mipmap.icon_theme_pine, "Description", "theme", false));
        StoreItem theme2 = (new StoreItem("Sand", 200, R.mipmap.icon_theme_sand, "Description", "theme", false));
        StoreItem theme3 = (new StoreItem("Swamp", 200, R.mipmap.icon_theme_swamp, "Description", "theme", false));
        StoreItem theme4 = (new StoreItem("Flower", 150, R.mipmap.icon_theme_flower, "Description", "theme", false));
        StoreItem theme5 = (new StoreItem("Beach", 120, R.mipmap.icon_theme_beach, "Description", "theme", false));

        StoreItem music1 = (new StoreItem("Guitar", 50, R.mipmap.icon_music_guitar, "Description", "music", false));
        StoreItem music2 = (new StoreItem("Fire camp", 40, R.mipmap.icon_music_firecamp, "Description", "music", false));
        StoreItem music3 = (new StoreItem("Rain", 30, R.mipmap.icon_music_rain, "Description", "music", false));
        StoreItem music4 = (new StoreItem("Piano", 70, R.mipmap.icon_music_piano, "Description", "music", false));

        StoreItem specialItem1 = (new StoreItem("Freeze", 50, R.mipmap.icon_freezing, "Description", "special item", false));
        StoreItem specialItem2 = (new StoreItem("Protection", 100, R.mipmap.icon_item_protection, "Description", "special item", false));
        StoreItem specialItem3 = (new StoreItem("Repair", 200, R.mipmap.icon_item_repair, "Description", "special item", false));
        StoreItem specialItem4 = (new StoreItem("Free week", 150, R.mipmap.icon_item_ticket, "Description", "special item", false));
        //endregion

        //region Add data into ViewModel
        // Add data into ViewModel only if the lists are empty
        if (storeItemViewModel.getStoreItemSize() == 0) {
            Toast.makeText(getContext(), String.valueOf(storeItemViewModel.getStoreItemSize()), Toast.LENGTH_LONG).show();
            storeItemViewModel.insertStoreItem(tree1);
            storeItemViewModel.insertStoreItem(tree2);
            storeItemViewModel.insertStoreItem(tree3);
            storeItemViewModel.insertStoreItem(tree4);
            storeItemViewModel.insertStoreItem(tree5);
            storeItemViewModel.insertStoreItem(tree6);
            storeItemViewModel.insertStoreItem(theme1);
            storeItemViewModel.insertStoreItem(theme2);
            storeItemViewModel.insertStoreItem(theme3);
            storeItemViewModel.insertStoreItem(theme4);
            storeItemViewModel.insertStoreItem(theme5);
            storeItemViewModel.insertStoreItem(music1);
            storeItemViewModel.insertStoreItem(music2);
            storeItemViewModel.insertStoreItem(music3);
            storeItemViewModel.insertStoreItem(music4);
            storeItemViewModel.insertStoreItem(specialItem1);
            storeItemViewModel.insertStoreItem(specialItem2);
            storeItemViewModel.insertStoreItem(specialItem3);
            storeItemViewModel.insertStoreItem(specialItem4);
        }
    }

    public static void storeNewMoney(int newMoney) {
        SharedPreferences.Editor editor = StoreFragment.coinSharedPreference.edit();
        editor.putInt("money", newMoney);
        editor.commit();
        tvCoinNumber.setText(String.valueOf(newMoney));
    }


}