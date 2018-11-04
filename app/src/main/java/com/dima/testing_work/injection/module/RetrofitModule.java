package com.dima.testing_work.injection.module;


import com.dima.testing_work.data.Network.model.EtsyAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    public EtsyAPI providesApi() {

        String BASE_URL = "https://openapi.etsy.com/v2/";

            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit.create(EtsyAPI.class);
    }
}
