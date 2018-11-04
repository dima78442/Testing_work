package com.dima.testing_work.data.Network.model;

import com.dima.testing_work.data.Network.model.model.Category.Response;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EtsyAPI {

    //https://openapi.etsy.com/v2/taxonomy/categories?api_key=l6pdqjuf7hdf97h1yvzadfce

    //https://openapi.etsy.com/v2/listings/active?includes=Images&api_key=l6pdqjuf7hdf97h1yvzadfce&category=paper_goods&keywords=terminator&limit=5&offset=0

    @GET("taxonomy/categories")
    Call<Response> getCategories(@Query("api_key") String api_key);


    @GET("listings/active")
    Call<ResponseSearch> getSearchResults(
            @Query("includes") String includes,@Query("api_key") String api_key,@Query("category")
            String category,@Query("keywords") String keywords,@Query("limit") int limit,
            @Query("offset") int offset);
}
