package com.e.nytimes.rest;

import com.e.nytimes.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.e.nytimes.util.AppConstant.API_KEY;

public interface EndPoints {
    @GET("svc/mostpopular/v2/viewed/1.json?api-key=" + API_KEY)
    Call<NewsResponse> getMostPopularNews();
}