package com.balu.komalexclusivenews.mvp.view;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.mvp.model.news.Article;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

public class ArticleCarouselFragment extends Fragment {

    private TextView description_tv, moreLink_tv;
    private ImageView newsImg_iv;
    private LinearLayout llparemntView;

    public static ArticleCarouselFragment newInstance(Article article) {

        ArticleCarouselFragment fragment = new ArticleCarouselFragment();

        fragment.mContent = article;
        return fragment;
    }

    private Article mContent ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup Rootview = (ViewGroup) inflater.inflate(R.layout.fragment_article_carousel,
                container, false);


        description_tv = Rootview.findViewById(R.id.tv_article_description);
        moreLink_tv = Rootview.findViewById(R.id.tv_article_link);
        newsImg_iv = Rootview.findViewById(R.id.iv_article_img);
        llparemntView = Rootview.findViewById(R.id.ll_news_parent);
        description_tv.setText(mContent.getDescription());
        moreLink_tv.setText(mContent.getUrl());
        //Glide.with(getActivity()).load(mContent.getUrlToImage()).placeholder(R.drawable.news_default).into(newsImg_iv);
        Glide.with(this).asBitmap().load(mContent.getUrlToImage()).placeholder(R.drawable.news_default).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                newsImg_iv.setImageBitmap(resource);
                setColors(resource);

            }
            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });

        return Rootview;
    }

    public void setColors(Bitmap bitmap){

        Palette p = Palette.from(bitmap).generate();

        getActivity().getWindow().setStatusBarColor(p.getLightMutedColor(
                getResources().getColor(R.color.colorAccent)));
//        llparemntView.setBackgroundDrawable(new ColorDrawable(p.getLightMutedColor(
//                getResources().getColor(R.color.colorPrimaryDark))));
        getActivity().setTitleColor(p.getLightVibrantColor(
                getResources().getColor(R.color.colorAccent)));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
