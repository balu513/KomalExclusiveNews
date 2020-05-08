package com.balu.komalexclusivenews.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.mvp.model.news.Article;
import com.balu.komalexclusivenews.mvp.view.adapter.ArticleDetailsPagerAdapter;
import com.balu.komalexclusivenews.view.carousel.ArticlesCarouselViewPager;

import java.util.ArrayList;

public class ArticleDetailActivity extends AppCompatActivity {

    private ArticlesCarouselViewPager mPager;
    private ArrayList<Article> articlesList;
    private int positionArticleSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            articlesList = bundle.getParcelableArrayList("Articles");
            positionArticleSelected = bundle.getInt("selectedArticle");
        }
        initArticlesCarouselViewPager();
    }

    private void initArticlesCarouselViewPager() {
        mPager = (ArticlesCarouselViewPager) findViewById(R.id.article_pager);
        ArticleDetailsPagerAdapter articleDetailsPagerAdapter = new ArticleDetailsPagerAdapter(getSupportFragmentManager(),
                ArticleDetailActivity.this, articlesList, positionArticleSelected);
        mPager.setAdapter(articleDetailsPagerAdapter);
        mPager.setCurrentItem(positionArticleSelected);
        mPager.setAnimationEnabled(true);
        mPager.setFadeEnabled(true);
        mPager.setFadeFactor(0.6f);
    }
}
