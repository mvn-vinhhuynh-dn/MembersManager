package com.asiantech.membersmanager.models;

import lombok.Data;

@Data
public class NavigationDrawerItem {
    private boolean showNotify;
    private String title;

    public NavigationDrawerItem() {
    }

    public NavigationDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }
}
