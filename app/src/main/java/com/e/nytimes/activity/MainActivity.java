package com.e.nytimes.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.e.nytimes.R;
import com.e.nytimes.adapter.NewsListAdapter;
import com.e.nytimes.model.Result;
import com.e.nytimes.viewmodel.MainActivityViewModel;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressBar loader;
    private RecyclerView rv;
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = findViewById(R.id.progress_bar);
        rv = findViewById(R.id.rv_main_activity);

        initRecyclerView();
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init();
        updateAdapter();
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

    private void updateAdapter() {
        viewModel.getLiveData().observe(this, newsResponse -> {
            if (newsResponse != null && "OK".equalsIgnoreCase(newsResponse.getStatus())) {
                Log.d(TAG, "updateAdapter: " + newsResponse.toString());
                List<Result> results = newsResponse.getResults();
                adapter.updateList(results);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initRecyclerView() {
        adapter = new NewsListAdapter(Collections.emptyList(), this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    public void openWebView(String url) {
        Intent myIntent = new Intent(this, WebViewActivity.class);
        myIntent.putExtra("url",url );
        this.startActivity(myIntent);
    }
}
