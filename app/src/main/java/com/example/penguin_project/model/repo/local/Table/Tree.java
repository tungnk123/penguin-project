package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tree")
public class Tree {
    @PrimaryKey(autoGenerate = true)
    private int Tree_id;

    private String Title;

    private int Icon;

    private int TimeToGrow;

    @Ignore
    public boolean isSelected;

    public boolean IsPurchased;

    public boolean getIsPurchased() {
        return IsPurchased;
    }

    public Tree( String title, int icon, int timeToGrow, boolean isPurchased) {
        Title = title;
        Icon = icon;
        TimeToGrow = timeToGrow;
        this.IsPurchased = isPurchased;
    }

    public void setPurchased(boolean purchased) {
        IsPurchased = purchased;
    }

    public int getTree_id() {
        return Tree_id;
    }

    public void setTree_id(int tree_id) {
        Tree_id = tree_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int Icon) {
        this.Icon = Icon;
    }

    public int getTimeToGrow() {
        return TimeToGrow;
    }

    public void setTimeToGrow(int TimeToGrow) {
        this.TimeToGrow = TimeToGrow;
    }

    public Tree(String Title, int Icon, int TimeToGrow) {
        this.Title = Title;
        this.Icon = Icon;
        this.TimeToGrow = TimeToGrow;
    }

    public Tree(int Tree_id, String Title, int icon, int IimeToGrow) {
        this.Tree_id = Tree_id;
        this.Title = Title;
        this.Icon = Icon;
        this.TimeToGrow = TimeToGrow;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean b) {
        isSelected = b;
    }
}
