package com.e.nytimes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.e.nytimes.model.NewsResponse;
import com.e.nytimes.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements Observer<NewsResponse> {

    private MainActivityViewModel viewModel;
    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressBar loader;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = findViewById(R.id.progress_bar);
        rv = findViewById(R.id.rv_main_activity);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init();
        viewModel.getLiveData().observe(this, this);

        setLoader();
    }

    private void setLoader() {
        viewModel.isUpdating().observe(this, loading -> {
            if (loading) {
                loader.setVisibility(View.VISIBLE);
            } else {
                loader.setVisibility(View.GONE);
            }
        });
    }

    private void updateAdapter(NewsResponse newsResponse) {
        if (newsResponse != null && "OK".equalsIgnoreCase(newsResponse.getStatus())) {
            Log.d(TAG, "updateAdapter: " + newsResponse.toString());

        }
    }

    @Override
    public void onChanged(@Nullable NewsResponse newsResponse) {
        updateAdapter(newsResponse);
    }
}
