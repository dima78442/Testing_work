package com.dima.testing_work.ui.untitled;

import android.util.Log;

import com.dima.testing_work.data.Network.model.EtsyAPI;
import com.dima.testing_work.data.Network.model.model.Category.ResponseCategory;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiTest {


    Retrofit retrofit;

    EtsyAPI apiService;


    public static final String BASE_URL = "https://openapi.etsy.com/v2/";


    public void setup(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService =
                retrofit.create(EtsyAPI.class);


    }

    public void action(){
        //System.out.println("work ");
        try {
            apiService.getCategories("l6pdqjuf7hdf97h1yvzadfce").enqueue(new Callback<ResponseCategory>() {
                @Override
                public void onResponse(Call<ResponseCategory> call, retrofit2.Response<ResponseCategory> response) {
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        Log.d("RetroLog",""+ response.body().getResults().get(i).getCategoryName());
                    }

                }

                @Override
                public void onFailure(Call<ResponseCategory> call, Throwable t) {
                    Log.d("RetroLog","fail" + t.getMessage());
                }

            });
            /*apiService.getCategories("l6pdqjuf7hdf97h1yvzadfce").enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, Response<Response> response) {
                    Log.d("RetroLog","work"+ response.body().getCategoryName());
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.d("RetroLog","fail" + t.getMessage());
                }*/
        } catch (Exception e) {
            Log.d("RetroLog","fail2");
        }
    }

    public void action2(){
        //https://openapi.etsy.com/v2/listings/active?includes=Images&api_key=l6pdqjuf7hdf97h1yvzadfce&category=paper_goods&keywords=terminator&limit=5&offset=0
        apiService.getSearchResults("Images","l6pdqjuf7hdf97h1yvzadfce","paper_goods","terminator",5,0).enqueue(new Callback<ResponseSearch>() {
            @Override
            public void onResponse(Call<ResponseSearch> call, retrofit2.Response<ResponseSearch> response) {
                Log.d("RetroLog","work"+ response.body().getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<ResponseSearch> call, Throwable t) {
                Log.d("RetroLog","fail" + t.getMessage());
            }
        });
    }
}