package com.dima.testing_work.injection.module;

import android.app.Application;
import android.content.Context;

import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.db.AppDbHelper;
import com.dima.testing_work.data.db.DbOpenHelper;
import com.dima.testing_work.injection.annotation.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


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

    @Singleton
    @Provides
    DataManager dataManager(DataManager dataManager){
        return dataManager;
    }

    @Singleton
    @Provides
    DbOpenHelper dbOpenHelper(DbOpenHelper dbOpenHelper){
        return dbOpenHelper;
    }

    @Singleton
    @Provides
    AppDbHelper appDbHelper(AppDbHelper appDbHelper){
        return appDbHelper;
    }

}