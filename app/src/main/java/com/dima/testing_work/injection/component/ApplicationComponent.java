

package com.dima.testing_work.injection.component;

import android.app.Application;
import android.content.Context;

import com.dima.testing_work.MyApplication;
import com.dima.testing_work.injection.annotation.ApplicationContext;
import com.dima.testing_work.injection.module.ApplicationModule;

import dagger.Component;



@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

    @ApplicationContext
    Context context();

    Application application();


}
