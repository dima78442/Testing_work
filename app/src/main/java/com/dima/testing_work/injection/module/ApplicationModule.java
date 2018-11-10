package com.dima.testing_work.injection.module;

import android.app.Application;
import android.content.Context;

import com.dima.testing_work.data.Network.model.EtsyAPI;
import com.dima.testing_work.injection.annotation.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    /*@Singleton
    @Provides
    DataManager provideDataManager(DataManager dataManager){
        return dataManager;
    }

    @Singleton
    @Provides
    DbOpenHelper provideDBOpenHelper(DbOpenHelper dbOpenHelper){
        return dbOpenHelper;
    }

    @Singleton
    @Provides
    AppDbHelper provideAppDbHelper(AppDbHelper appDbHelper){
        return appDbHelper;
    }

    @Singleton
    @Provides
    EtsyNetwork prividesEtsyNetwork(EtsyNetwork etsyNetwork){ return etsyNetwork;}*/

    @Singleton
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