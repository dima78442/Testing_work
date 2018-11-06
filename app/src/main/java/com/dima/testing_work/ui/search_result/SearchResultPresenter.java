package com.dima.testing_work.ui.search_result;

import android.util.Log;
import android.widget.ImageView;

import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.Network.model.PicassoEtsy;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultPresenter {

    private SearchResultMvpView mvpView;
    private DataManager dataManager;
    private List<String> results;
    private ResponseSearch responseSearch;

    private boolean isLoading = true;
    private boolean isLastPage= false;

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

    public void getSearchResults(
            String includes,String api_key,
            String category,String keywords,int limit,
            int offset){
        isLoading = true;
        dataManager.getSearchResults(includes, api_key, category, keywords, limit, offset).enqueue(new Callback<ResponseSearch>() {
            @Override
            public void onResponse(Call<ResponseSearch> call, Response<ResponseSearch> response) {
                if(isViewAttached()){
                    responseSearch = response.body();
                    isLoading = false;
                    Log.d("Retro3","work" + response.body().getResults().get(0).getImages().get(1).getUrlFullxfull());
                    if (response.body().getPagination().getNextPage() == null) {
                        isLastPage = true;

                    }
                    mvpView.results_process();
                    mvpView.updateView();
                }
            }

            @Override
            public void onFailure(Call<ResponseSearch> call, Throwable t) {
                isLoading = false;
                responseSearch = null;
            }
        });

    }
    public static void setImage(String url, ImageView image){
        PicassoEtsy.imageDownload(url, image);
    }

    public ResponseSearch getResponseSearch() {
        return responseSearch;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isLastPage() {
        return isLastPage;
    }
}
