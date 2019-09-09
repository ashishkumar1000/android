package com.e.nytimes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.nytimes.R;
import com.e.nytimes.activity.MainActivity;
import com.e.nytimes.model.Medium;
import com.e.nytimes.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.CustomViewHolder> {


    private List<Result> results;
    private Context context;

    public NewsListAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder h, int i) {
        Result result = results.get(i);
        h.author.setText(result.getByline());
        h.newsHeading.setText(result.getTitle());
        setDate(h.date, result);
        setImage(h.newsImage, result.getMedia().get(0));
        h.itemView.setOnClickListener(v -> {
            ((MainActivity) context).openWebView(result.getUrl());
        });
    }

    private void setImage(ImageView newsImage, Medium medium) {
        setImage(newsImage, medium.getMediaMetadata().get(0).getUrl());
    }

    private void setDate(TextView tv, Result result) {
        String date = result.getPublishedDate();
        if (date != null && date.length() > 0) {
            tv.setText(date);
        }
    }

    private void setImage(ImageView ivImage, String url) {
        Picasso.with(context)
                .load(url)
                .placeholder(context.getDrawable(R.drawable.ic_launcher_background))
                .fit()
                .centerCrop()
                .into(ivImage);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void updateList(List<Result> results) {
        this.results = results;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView newsHeading;
        private ImageView newsImage;
        private TextView author;
        private TextView date;

        public CustomViewHolder(@NonNull View v) {
            super(v);
            newsHeading = v.findViewById(R.id.tv_new_heading);
            newsImage = v.findViewById(R.id.profile_image);
            date = v.findViewById(R.id.tv_date);
            author = v.findViewById(R.id.tv_author);
        }
    }
}
