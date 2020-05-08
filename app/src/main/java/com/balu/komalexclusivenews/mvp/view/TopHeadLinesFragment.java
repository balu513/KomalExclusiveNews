package com.balu.komalexclusivenews.mvp.view;


import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TopHeadLinesFragment extends Fragment implements IView.ITopHeadLines, TopHeadLinesAdapter.ArticleItemClickListener {

    @BindView(R.id.top_headlines_recycleView) RecyclerView recyclerView;
    private TopHeadLinesPresenter topHeadLinesPresenter;
    private String countrycode;
    @BindView(R.id.news_loading) View newsLoader;
    private ArrayList<Article> articles;

    @Inject
    NewsApiInterface newsApiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_head_lines, container, false);
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
}
