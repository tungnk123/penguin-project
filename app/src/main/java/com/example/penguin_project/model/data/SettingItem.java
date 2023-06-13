package com.example.penguin_project.model.data;

public class SettingItem {
    private String title;
    private int icon;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SettingItem(String title, int icon, String status) {
        this.title = title;
        this.icon = icon;
        this.status = status;
    }
}
