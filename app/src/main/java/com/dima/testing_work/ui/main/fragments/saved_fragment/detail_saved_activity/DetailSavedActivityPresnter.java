package com.dima.testing_work.ui.main.fragments.saved_fragment.detail_saved_activity;

import android.widget.ImageView;

import com.dima.testing_work.data.DataManager;

public class DetailSavedActivityPresnter {

    public static void setImage(String url, ImageView image){
        DataManager.setImage(url,image);
    }
}


