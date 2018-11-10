
package com.dima.testing_work.injection.module;

import android.app.Activity;
import android.content.Context;

import com.dima.testing_work.injection.annotation.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amitshekhar on 13/01/17.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }

    /*@Provides
    @PerActivity
    DetailActivityPresenter provideDetailPresenter(DetailActivityPresenter presenter){
        return presenter;
    }*/

   /* @Provides
    MyPagerAdapter providesMyPageAdapter(){
        FragmentActivity myActivity  = (FragmentActivity)mActivity;
        return new MyPagerAdapter(myActivity.getSupportFragmentManager());
    }*/


}
