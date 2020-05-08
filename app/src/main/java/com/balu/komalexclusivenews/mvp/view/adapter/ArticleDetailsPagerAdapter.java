package com.balu.komalexclusivenews.mvp.view.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.mvp.model.news.Article;
import com.balu.komalexclusivenews.mvp.view.ArticleCarouselFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ArticleDetailsPagerAdapter extends FragmentStatePagerAdapter {
    private final Context context;
    private final List<Article> articleList;
    private final int selectedArticle;

    public ArticleDetailsPagerAdapter(@NonNull FragmentManager fm, Context context, List<Article> articleList, int selectedArticle) {
        super(fm);
        this.context = context;
        this.articleList = articleList;
        this.selectedArticle = selectedArticle;
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (object != null) {
            return ((Fragment) object).getView() == view;
        } else {
            return false;
        }
    }
    @Override
    public int getCount() {
        return articleList == null ? 0 : articleList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
         return ArticleCarouselFragment.newInstance(articleList.get(position));
    }
    @Override
    public int getItemPosition(Object object) {
        // Causes adapter to reload all Fragments when
        // notifyDataSetChanged is called
        return POSITION_NONE;
    }

    }
