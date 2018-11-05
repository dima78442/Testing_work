package com.dima.testing_work.data.Network.model;

import com.dima.testing_work.data.Network.model.model.Category.ResponseCategory;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;
import com.dima.testing_work.injection.component.DaggerRetrofitComponent;
import com.dima.testing_work.injection.component.RetrofitComponent;
import com.dima.testing_work.injection.module.RetrofitModule;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class EtsyNetwork {

    @Inject
    EtsyAPI etsyAPI;

    public static String API_KEY = "l6pdqjuf7hdf97h1yvzadfce";
    private List<String> categories;
    private RetrofitComponent retrofitComponent;


    public EtsyNetwork() {
        retrofitComponent = DaggerRetrofitComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build();
        retrofitComponent.inject(this);
    }

    public Call<ResponseCategory> getCategories(String api_key){
        return etsyAPI.getCategories(api_key);
    }

    public Call<ResponseSearch> getSearchResults(
            String includes,String api_key,
            String category,String keywords,int limit,
             int offset){
        return etsyAPI.getSearchResults(includes,api_key,category,keywords,limit,offset);
    }
   /* public List<String> getCategories(String api_key){

        categories = new ArrayList<>();
        etsyAPI.getCategories(api_key).enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                Log.d("Retro","work" + response.body().getResults().get(0).getCategoryName());
                List<Result> results = response.body().getResults();
                for (int j = 0; j < results.size(); j++) {
                   categories.add(results.get(j).getCategoryName());
                }
            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                Log.d("Retro","fail");
                categories = null;
            }
        });
        return categories;
    }
*/

}
