package com.dima.testing_work.ui.main.fragments.saved_fragment;

import com.dima.testing_work.data.db.model.ItemSaved;

import java.util.List;

public interface SavedMvpView {
    void setData(List<ItemSaved> items);
    void updateView();

}