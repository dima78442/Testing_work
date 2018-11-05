package com.dima.testing_work.ui.search_result;

import com.dima.testing_work.data.DataManager;

import java.util.List;

public class SearchResultPresenter {

    private SearchResultMvpView mvpView;
    private DataManager dataManager;
    private List<String> results;


    public SearchResultPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void onAttach(SearchResultMvpView mvpView) {
        this.mvpView = mvpView;
    }


    public void onDetach() {
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    /*public ResponseSearch getSearchResults{
        dataManager.getSearchResults
    }*/
}
