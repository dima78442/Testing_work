package com.dima.testing_work.ui.detail;

import android.widget.ImageView;

import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.Network.model.PicassoEtsy;
import com.dima.testing_work.data.db.model.ItemSaved;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailActivityPresenter {

    private DetailMvpView mvpView;
    private DataManager dataManager;
    private final String message= "Successfully added to saved";

    public DetailActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void onAttach(DetailMvpView mvpView) {
        this.mvpView = mvpView;
    }


    public void onDetach() {
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public void insetItem(final ItemSaved item){
        dataManager.insertItem(item).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                if (isViewAttached()) {
                    mvpView.makeToast(message+ aLong);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
    }


    public void setImage(String url, ImageView image){
        PicassoEtsy.imageDownload(url, image);
    }

    public ItemSaved itemCreator(String name,String description,String price,String img){
        return new ItemSaved(null,name,description,price,img);
    }
}