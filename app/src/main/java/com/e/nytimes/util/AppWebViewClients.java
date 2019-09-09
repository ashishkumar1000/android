package com.e.nytimes.util;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AppWebViewClients extends WebViewClient {
    private ProgressBar progressBar;

    public AppWebViewClients(ProgressBar progressBar) {
        this.progressBar = progressBar;
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);
    }
}