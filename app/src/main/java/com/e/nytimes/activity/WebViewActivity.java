package com.e.nytimes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.e.nytimes.R;
import com.e.nytimes.util.AppWebViewClients;

public class WebViewActivity extends AppCompatActivity {

    private ProgressBar loader;
    private ImageView back;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_web_view);
        loader = findViewById(R.id.loader);
        back = findViewById(R.id.iv_back);
        back.setOnClickListener(v -> onBackPressed());


        String url = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
        }
        WebView wv = findViewById(R.id.web_view);
        wv.setWebViewClient(new AppWebViewClients(loader));
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.loadUrl(url);
    }
}