package com.dima.testing_work.injection.component;


import com.dima.testing_work.data.Network.model.EtsyNetwork;
import com.dima.testing_work.injection.module.RetrofitModule;

import dagger.Component;


@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    void inject(EtsyNetwork etsyNetwork);
}
