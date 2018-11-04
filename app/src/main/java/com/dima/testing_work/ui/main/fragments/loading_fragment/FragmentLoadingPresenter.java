package com.dima.testing_work.ui.main.fragments.loading_fragment;

import android.util.Log;

import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.Network.model.model.Category.ResponseCategory;
import com.dima.testing_work.data.Network.model.model.Category.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLoadingPresenter {

    private LoadingMvpView mvpView;
    private DataManager dataManager;
    private List<String> categories;

    public FragmentLoadingPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void onAttach(LoadingMvpView mvpView) {
        this.mvpView = mvpView;
    }


    public void onDetach() {
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    List<String> getCategories(String api_key){
        categories = new ArrayList<>();
        dataManager.getCategories(api_key).enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                Log.d("Retro2","work");
                for (Result result: response.body().getResults()) {
                    categories.add(result.getCategoryName());
                }
                if(isViewAttached()){
                    mvpView.toSearch(categories);
                }
            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                categories = null;
            }
        });
        return categories;
    }
}
