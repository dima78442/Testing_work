package com.dima.testing_work.data.Network.model;

import com.dima.testing_work.data.Network.model.model.Category.ResponseCategory;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;
import com.dima.testing_work.injection.component.RetrofitComponent;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EtsyNetwork {

    EtsyAPI etsyAPI;

    public static String API_KEY = "l6pdqjuf7hdf97h1yvzadfce";
    private List<String> categories;
    private RetrofitComponent retrofitComponent;

    @Inject
    public EtsyNetwork() {
        String BASE_URL = "https://openapi.etsy.com/v2/";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        etsyAPI =  retrofit.create(EtsyAPI.class);
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
