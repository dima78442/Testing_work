
package com.dima.testing_work.injection.component;


import com.dima.testing_work.injection.annotation.PerActivity;
import com.dima.testing_work.injection.module.ActivityModule;
import com.dima.testing_work.ui.main.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
