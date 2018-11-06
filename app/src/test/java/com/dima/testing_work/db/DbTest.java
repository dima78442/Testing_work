package com.dima.testing_work.db;


import com.dima.testing_work.data.db.AppDbHelper;
import com.dima.testing_work.data.db.DbOpenHelper;
import com.dima.testing_work.data.db.model.ItemSaved;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@RunWith(RobolectricTestRunner.class)
public class DbTest {

    DbOpenHelper dbOpenHelper;
    AppDbHelper appDbHelper;
    ItemSaved itemSaved;

    @Before
    public void setup(){

        dbOpenHelper = new DbOpenHelper(RuntimeEnvironment.application);
        appDbHelper = new AppDbHelper(dbOpenHelper);
        ShadowLog.stream = System.out;


    }

    @Test
    public void action(){
        long i = 0;
        itemSaved = new ItemSaved(i,"dima","des","price","img.png");
        appDbHelper.insertItem(itemSaved).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        final ItemSaved itemSaved2 = new ItemSaved(null,"andriy","des","price","img.png");
        appDbHelper.insertItem(itemSaved2).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        appDbHelper.getAllItems().subscribe(new Observer<List<ItemSaved>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ItemSaved> itemSaveds) {
                System.out.println(itemSaveds.get(0).getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("done");
            }
        });
        appDbHelper.isSavedItemsEmpty().subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println(aBoolean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        appDbHelper.getItemKey(itemSaved2).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("this");
                System.out.println(aLong);
                System.out.println("this");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        appDbHelper.deleteItem(itemSaved).subscribe(new Observer<Void>() {
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
        appDbHelper.deleteItemById(Integer.toUnsignedLong(1)).subscribe(new Observer<Void>() {
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
        appDbHelper.getAllItems().subscribe(new Observer<List<ItemSaved>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ItemSaved> itemSaveds) {
                System.out.println(itemSaveds.size());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Test
    public void getKey(){

    }



}
