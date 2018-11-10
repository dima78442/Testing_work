package com.dima.testing_work.data.Network.model;

import com.dima.testing_work.data.Network.model.model.Category.ResponseCategory;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class EtsyNetwork {

    @Inject
    EtsyAPI etsyAPI;

    public static String API_KEY = "l6pdqjuf7hdf97h1yvzadfce";
    private List<String> categories;


    @Inject
    public EtsyNetwork() {
    }

    public Call<ResponseCategory> getCategories(String api_key){
        return etsyAPI.getCategories(api_key);
    }

    public Call<ResponseSearch> getSearchResults(
            String includes,String api_key,
            String category,String keywords,int limit,
             int offset){
        return etsyAPI.getSearchResults(includes,api_key,category,keywords,limit,offset);
    }

}
