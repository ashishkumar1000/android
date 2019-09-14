package com.e.nytimes.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.e.nytimes.NetworkErrorFragment;
import com.e.nytimes.R;
import com.e.nytimes.adapter.NewsListAdapter;
import com.e.nytimes.model.Result;
import com.e.nytimes.viewmodel.MainActivityViewModel;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NetworkErrorFragment.OnNetworkErrorFragmentInteractionListener {

    private MainActivityViewModel viewModel;
    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressBar loader;
    private RecyclerView rv;
    private NewsListAdapter adapter;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = findViewById(R.id.progress_bar);
        rv = findViewById(R.id.rv_main_activity);
        swipeContainer = findViewById(R.id.swipeContainer);


        initRecyclerView();
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init();
        updateAdapter();
        setLoader();
        initNetworkError();

        swipeContainer.setOnRefreshListener(this);
        checkInternet();
    }

    private void initNetworkError() {
        viewModel.errorPage().observe(this, show ->{
            if (show){
                showNetworkError();
            }else{
                hideNetworkError();
            }
        });
    }

    private void checkInternet() {
        if (!isNetworkConnected()) {
            Snackbar.make(findViewById(R.id.rl_main), "NO INTERNET!! Please swipe to refresh once network is back.", Snackbar.LENGTH_SHORT).show();
            showNetworkError();
        }
    }

    private void setLoader() {
        viewModel.isUpdating().observe(this, loading -> {
            if (loading) {
                loader.setVisibility(View.VISIBLE);
            } else {
                loader.setVisibility(View.GONE);
                swipeContainer.setRefreshing(false);
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

    public void openWebView(Result result) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.DATA, result);
        this.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        if (isNetworkConnected()) {
            Snackbar.make(findViewById(R.id.rl_main), "Refreshing data from server...", Snackbar.LENGTH_SHORT).show();
            viewModel.fetchDataFromApi();
        } else {
            Snackbar.make(findViewById(R.id.rl_main), "NO INTERNET!! Please swipe to refresh once network is back.", Snackbar.LENGTH_SHORT).show();
            swipeContainer.setRefreshing(false);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onRetryClickedFromNetworkErrorPage() {
        onRefresh();
    }

    private void showNetworkError() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_error_layout, NetworkErrorFragment.newInstance(), NetworkErrorFragment.class.getName())
                .commitAllowingStateLoss();
    }

    private void hideNetworkError() {
        Fragment frag = getSupportFragmentManager().findFragmentByTag(NetworkErrorFragment.class.getName());
        if (frag != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(frag);
            transaction.commit();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            frag = null;
        }
    }
}
