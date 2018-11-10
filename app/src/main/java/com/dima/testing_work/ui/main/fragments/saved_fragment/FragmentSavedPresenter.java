package com.dima.testing_work.ui.main.fragments.saved_fragment;

import android.widget.ImageView;

import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.db.model.ItemSaved;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentSavedPresenter {

    private SavedMvpView mvpView;
    private DataManager dataManager;

    @Inject
    public FragmentSavedPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void onAttach(SavedMvpView mvpView) {
        this.mvpView = mvpView;
    }

    public void onDetach() {
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public static void setImage(String url, ImageView image){ DataManager.setImage(url, image); }

    public ItemSaved itemCreator(String name, String description, String price, String img){
        return new ItemSaved(null,name,description,price,img);
    }

    public void getAllItems(){
        dataManager.getAllItems().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<ItemSaved>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ItemSaved> itemSaveds) {
                if (isViewAttached()) {
                    mvpView.setData(itemSaveds);
                    mvpView.updateView();
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

    public void deleteItem(ItemSaved item){
        dataManager.deleteItem(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Void>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Void aVoid) {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}