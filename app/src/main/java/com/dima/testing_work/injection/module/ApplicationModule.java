package com.dima.testing_work.injection.module;

import android.app.Application;
import android.content.Context;

import com.dima.testing_work.injection.annotation.ApplicationContext;

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

}