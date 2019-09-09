package com.e.nytimes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.e.nytimes.model.NewsResponse;
import com.e.nytimes.rest.ApiClient;
import com.e.nytimes.rest.EndPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel implements Callback<NewsResponse> {
    private String TAG = MainActivityViewModel.class.getSimpleName();
    private MutableLiveData<NewsResponse> mutableLiveData;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void fetchDataFromApi() {
        isUpdating.postValue(true);
        ApiClient.getClient().create(EndPoints.class).getMostPopularNews().enqueue(this);
    }

    //LiveData is immutable and it cannot be changed.
    public LiveData<NewsResponse> getLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void init() {
        if (mutableLiveData == null) {
            fetchDataFromApi();
        }
    }

    public LiveData<Boolean> isUpdating() {
        return isUpdating;
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        NewsResponse newsResponse = response.body();
        mutableLiveData.postValue(newsResponse);
        isUpdating.postValue(false);
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        Log.d(TAG, "On failure response: " + t);
        isUpdating.postValue(false);
    }
}
