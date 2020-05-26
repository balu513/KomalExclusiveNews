package com.balu.komalexclusivenews.mvp.view;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;


import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.application.KomalExclusiveNewsApplication;
import com.balu.komalexclusivenews.model.NewsApiInterface;
import com.balu.komalexclusivenews.mvp.model.news.Article;
import com.balu.komalexclusivenews.mvp.model.news.TopHeadlines;
import com.balu.komalexclusivenews.mvp.presenter.TopHeadLinesPresenter;
import com.balu.komalexclusivenews.mvp.view.adapter.TopHeadLinesAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.supercharge.shimmerlayout.ShimmerLayout;

public class TopHeadLinesFragment extends Fragment implements IView.ITopHeadLines, TopHeadLinesAdapter.ArticleItemClickListener {

    @BindView(R.id.top_headlines_recycleView) RecyclerView recyclerView;
    private TopHeadLinesPresenter topHeadLinesPresenter;
    private String countrycode;
    @BindView(R.id.news_loading) View newsLoader;
    private ArrayList<Article> articles;
    public LinearLayout skeletonLayout;
    public ShimmerLayout shimmer;
    public LayoutInflater inflater;

    @Inject
    NewsApiInterface newsApiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_head_lines, container, false);

        skeletonLayout = view.findViewById(R.id.skeletonLayout);
        shimmer = view.findViewById(R.id.shimmerSkeleton);
        showSkeleton(true);
        ButterKnife.bind(this, view);
        KomalExclusiveNewsApplication.getKomalNewsComponent().inject(this);
        if(getArguments() != null){
             countrycode = getArguments().getString("countrycode");
        }
        newsLoader.setVisibility(View.VISIBLE);
        topHeadLinesPresenter = new TopHeadLinesPresenter(getLifecycle(), this, newsApiInterface);
        topHeadLinesPresenter.getTopHeadLines(countrycode);

        return view;
    }

    @Override
    public void OnTopHeadLinesSuccess(TopHeadlines topHeadLines) {
        showSkeleton(false);
        articles = (ArrayList<Article>) topHeadLines.getArticles();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        TopHeadLinesAdapter adapter = new TopHeadLinesAdapter(getActivity().getApplicationContext(), topHeadLines.getArticles(),this);
        recyclerView.setAdapter(adapter);
        newsLoader.setVisibility(View.GONE);
    }

    @Override
    public void OnTopHeadLinesFailure(String errorMsg) {
        newsLoader.setVisibility(View.GONE);
        Toast.makeText(getActivity().getApplicationContext(),errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(),ArticleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Articles",articles);
        bundle.putInt("selectedArticle", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public int getSkeletonRowCount(Context context) {
        int pxHeight = getDeviceHeight(context);
//        int skeletonRowHeight = (int) getResources()
//                .getDimension(R.dimen.row_layout_height); //converts to pixel
//        return (int) Math.ceil(pxHeight / skeletonRowHeight);
        return 4;
    }
    public int getDeviceHeight(Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
    public void showSkeleton(boolean show) {

        if (show) {

            skeletonLayout.removeAllViews();

            int skeletonRows = getSkeletonRowCount(getContext());
            for (int i = 0; i <= skeletonRows; i++) {
                ViewGroup rowLayout = (ViewGroup) inflater
                        .inflate(R.layout.skeleton_row_layout, null);
                skeletonLayout.addView(rowLayout);
            }
            shimmer.setVisibility(View.VISIBLE);
            shimmer.startShimmerAnimation();
            skeletonLayout.setVisibility(View.VISIBLE);
            skeletonLayout.bringToFront();
        } else {
            shimmer.stopShimmerAnimation();
            shimmer.setVisibility(View.GONE);
        }
    }
}
