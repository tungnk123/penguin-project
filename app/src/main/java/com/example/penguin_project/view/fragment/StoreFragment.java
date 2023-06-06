package com.example.penguin_project.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.view.adapter.StoreItemAdapter;

import java.util.ArrayList;
import java.util.List;


public class StoreFragment extends Fragment {

    private RecyclerView rcvTreeItems;
    private RecyclerView rcvMusicItems;
    private RecyclerView rcvThemeItems;
    private RecyclerView rcvSpecialItems;

    private StoreItemAdapter storeItemAdapter;

    private List<StoreItem> treeItemsList;
    private List<StoreItem> musicItemsList;
    private List<StoreItem> themeItemsList;
    private List<StoreItem> specialItemsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        //region Anh xa
        rcvTreeItems = view.findViewById(R.id.rcv_storeFragment_treeList);
        rcvThemeItems = view.findViewById(R.id.rcv_storeFragment_themeList);
        rcvMusicItems = view.findViewById(R.id.rcv_storeFragment_musicList);
        rcvSpecialItems = view.findViewById(R.id.rcv_storeFragment_specialItemsList);
        //endregion
        //region Set Layout Manager
        rcvTreeItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvMusicItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvThemeItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));;
        rcvSpecialItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //endregion

        //region Set data for list
        treeItemsList = new ArrayList<>();
        musicItemsList = new ArrayList<>();
        themeItemsList = new ArrayList<>();
        specialItemsList = new ArrayList<>();

        treeItemsList.add(new StoreItem("Cay ABC", 12, R.mipmap.icon_bonsai, "Description"));
        treeItemsList.add(new StoreItem("Cay A", 2, R.mipmap.oak, "Description"));
        treeItemsList.add(new StoreItem("Cay B", 122, R.mipmap.icon_bonsai_1, "Description"));
        treeItemsList.add(new StoreItem("Cay C", 120, R.mipmap.icon_bonsai_2, "Description"));

        themeItemsList.add(new StoreItem("Cay ABC", 12, R.mipmap.icon_bonsai, "Description"));
        themeItemsList.add(new StoreItem("Cay A", 2, R.mipmap.oak, "Description"));
        themeItemsList.add(new StoreItem("Cay B", 122, R.mipmap.icon_bonsai_1, "Description"));
        themeItemsList.add(new StoreItem("Cay C", 120, R.mipmap.icon_bonsai_2, "Description"));

        musicItemsList.add(new StoreItem("Cay ABC", 12, R.mipmap.icon_bonsai, "Description"));
        musicItemsList.add(new StoreItem("Cay A", 2, R.mipmap.oak, "Description"));
        musicItemsList.add(new StoreItem("Cay B", 122, R.mipmap.icon_bonsai_1, "Description"));
        musicItemsList.add(new StoreItem("Cay C", 120, R.mipmap.icon_bonsai_2, "Description"));

        specialItemsList.add(new StoreItem("Cay ABC", 12, R.mipmap.icon_bonsai, "Description"));
        specialItemsList.add(new StoreItem("Cay A", 2, R.mipmap.oak, "Description"));
        specialItemsList.add(new StoreItem("Cay B", 122, R.mipmap.icon_bonsai_1, "Description"));
        specialItemsList.add(new StoreItem("Cay C", 120, R.mipmap.icon_bonsai_2, "Description"));
        //endregion
        //region Set Adapter cho RecyclerView
        storeItemAdapter = new StoreItemAdapter(treeItemsList, this){

        };
        rcvTreeItems.setAdapter(storeItemAdapter);
        storeItemAdapter = new StoreItemAdapter(themeItemsList, this){

        };
        rcvThemeItems.setAdapter(storeItemAdapter);
        storeItemAdapter = new StoreItemAdapter(musicItemsList, this){

        };
        rcvMusicItems.setAdapter(storeItemAdapter);
        storeItemAdapter = new StoreItemAdapter(specialItemsList, this){

        };
        rcvSpecialItems.setAdapter(storeItemAdapter);

        //endregion
        return view;
    }
}