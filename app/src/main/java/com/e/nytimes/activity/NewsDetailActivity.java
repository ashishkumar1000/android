package com.e.nytimes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.nytimes.R;
import com.e.nytimes.model.MediaMetadatum;
import com.e.nytimes.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsDetailActivity extends AppCompatActivity {

    public static final String DATA = "DATA";
    private static final String TAG = NewsDetailActivity.class.getSimpleName();
    private ImageView image;
    private TextView authorName;
    private TextView newsHeading;
    private TextView newsContents;
    private TextView newsDate;
    private TextView hyperlink;
    private ImageView back;
    private ImageView share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        image = findViewById(R.id.iv_news_detail);
        authorName = findViewById(R.id.tv_author_name);
        newsHeading = findViewById(R.id.news_heading);
        newsDate = findViewById(R.id.tv_date);
        newsContents = findViewById(R.id.tv_news_content);
        hyperlink = findViewById(R.id.tv_hyperlink);
        back = findViewById(R.id.iv_back);
        share = findViewById(R.id.iv_share);

        Result result = getIntent().getParcelableExtra(DATA);
        populateView(result);
    }

    private void populateView(Result result) {
        List<MediaMetadatum> lst = result.getMedia().get(0).getMediaMetadata();

        if (lst.size() == 3) {
            String url = lst.get(2).getUrl();
            setImage(url);
        } else if (lst.size() == 2) {
            String url = lst.get(1).getUrl();
            setImage(url);
        } else {
            setImage(lst.get(0).getUrl());
        }

        authorName.setText(result.getByline());
        newsContents.setText(result.getAbstract());
        newsHeading.setText(result.getTitle());
        newsDate.setText(result.getPublishedDate());

        hyperlink.setOnClickListener(v -> openWebView(result.getUrl()));
        back.setOnClickListener(v -> onBackPressed());
        share.setOnClickListener(v -> shareNews(result));
    }

    private void openWebView(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }


    private void setImage(String url) {
        Picasso.with(this)
                .load(url)
                .placeholder(this.getDrawable(R.drawable.ic_launcher_background))
                .fit()
                .centerCrop()
                .into(image);
    }

    private void shareNews(Result result) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, result.getTitle());
            String shareMessage = result.getAbstract() + "\n\n"+result.getUrl();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Choose one"));
        } catch (Exception e) {
            Log.d(TAG, "error: " + e.getMessage(), e);
        }
    }
}
