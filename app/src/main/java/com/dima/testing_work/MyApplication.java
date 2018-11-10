package com.dima.testing_work;

import android.app.Application;

import com.dima.testing_work.injection.component.ApplicationComponent;
import com.dima.testing_work.injection.component.DaggerApplicationComponent;
import com.dima.testing_work.injection.module.ApplicationModule;

public class MyApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
