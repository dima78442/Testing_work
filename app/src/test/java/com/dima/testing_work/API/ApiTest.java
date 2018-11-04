package com.dima.testing_work.API;


import com.dima.testing_work.data.Network.model.EtsyAPI;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@RunWith(JUnit4.class)
public class ApiTest {


    Retrofit retrofit;

    EtsyAPI apiService;

    public static final String BASE_URL = "https://openapi.etsy.com/v2/";

    @Before
    public void setup(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService =
                retrofit.create(EtsyAPI.class);

    }

    @Test
    public void action(){
        System.out.println("work ");
        try {
            apiService.getCategories("l6pdqjuf7hdf97h1yvzadfce").enqueue(new Callback<List<Result>>() {
                @Override
                public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                    System.out.println("this");
                    Assert.assertEquals("suc",32,response.body().size());
                }

                @Override
                public void onFailure(Call<List<Result>> call, Throwable t) {
                    Assert.assertEquals("suc",1,2);
                    System.out.println("no connection ");
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
