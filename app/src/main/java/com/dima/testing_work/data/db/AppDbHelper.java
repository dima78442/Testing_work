/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.dima.testing_work.data.db;


import com.dima.testing_work.data.db.model.DaoMaster;
import com.dima.testing_work.data.db.model.DaoSession;
import com.dima.testing_work.data.db.model.ItemSaved;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;



public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertItem(final ItemSaved item) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getItemSavedDao().insert(item);
            }
        });
    }

    @Override
    public Observable<List<ItemSaved>> getAllItems() {
        return Observable.fromCallable(new Callable<List<ItemSaved>>() {
            @Override
            public List<ItemSaved> call() throws Exception {
                return mDaoSession.getItemSavedDao().loadAll();
            }
        });
    }


    @Override
    public Observable<Boolean> isSavedItemsEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getItemSavedDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Void> deleteItem(final ItemSaved item) {
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                mDaoSession.getItemSavedDao().delete(item);
                return null;
            }
        });
    }

    @Override
    public Observable<Void> deleteItemById(final long id) {
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                mDaoSession.getItemSavedDao().deleteByKey(id);
                return null;
            }
        });
    }

    public Observable<Long> getItemKey(final ItemSaved item){
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getItemSavedDao().getKey(item);
            }
        });
    }

}
