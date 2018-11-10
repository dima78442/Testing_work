
package com.dima.testing_work.injection.component;


import com.dima.testing_work.injection.annotation.PerActivity;
import com.dima.testing_work.injection.module.ActivityModule;
import com.dima.testing_work.ui.detail.DetailActivity;
import com.dima.testing_work.ui.main.fragments.loading_fragment.Loading;
import com.dima.testing_work.ui.main.fragments.saved_fragment.Saved;
import com.dima.testing_work.ui.search_result.SearchResultActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(DetailActivity detailActivity);

    void inject(Loading loading);

    void inject(Saved saved);

    void inject(SearchResultActivity searchResultActivity);



}
