package com.dima.testing_work.data;

import android.widget.ImageView;

import com.dima.testing_work.data.Network.model.EtsyNetwork;
import com.dima.testing_work.data.Network.model.PicassoEtsy;
import com.dima.testing_work.data.Network.model.model.Category.ResponseCategory;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;
import com.dima.testing_work.data.db.AppDbHelper;
import com.dima.testing_work.data.db.model.ItemSaved;
import com.squareup.picasso.Target;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;


public class DataManager {

    private AppDbHelper appDbHelper;
    private EtsyNetwork etsyNetwork;


    public DataManager(AppDbHelper appDbHelper, EtsyNetwork etsyNetwork) {
        this.appDbHelper = appDbHelper;
        this.etsyNetwork = etsyNetwork;
    }

    public Call<ResponseCategory> getCategories(String api_key){
        return etsyNetwork.getCategories(api_key);
    }

    public Call<ResponseSearch> getSearchResults(
            String includes,String api_key,
            String category,String keywords,int limit,
            int offset) {
        return etsyNetwork.getSearchResults(includes, api_key, category, keywords, limit, offset);
    }

    public Observable<Long> insertItem(final ItemSaved item){
        return appDbHelper.insertItem(item);
    }

    public Observable<List<ItemSaved>> getAllItems(){
        return appDbHelper.getAllItems();
    }

    public Observable<Void> deleteItem(ItemSaved item){
        return appDbHelper.deleteItem(item);
    }

    public Observable<Void> deleteItemById(long id){
        return appDbHelper.deleteItemById(id);
    }

    public Observable<Boolean> isSavedItemsEmpty(){
        return appDbHelper.isSavedItemsEmpty();
    }

    public Observable<Long> getItemKey(final ItemSaved item){
        return appDbHelper.getItemKey(item);
    }
    public static void setImage(String url, ImageView image){
        PicassoEtsy.imageDownload(url, image);
    }
    public static void  saveImageFile(String url, ImageView image, Target target){
        PicassoEtsy.imageDownloadFile(url, image, target);
    }
}
