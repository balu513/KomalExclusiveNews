package com.balu.komalexclusivenews.view;


import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.model.news.Article;
import com.balu.komalexclusivenews.model.news.TopHeadlines;
import com.balu.komalexclusivenews.apiClient.NewsApiClient;
import com.balu.komalexclusivenews.presenter.TopHeadLinesPresenter;
import com.balu.komalexclusivenews.view.Adapter.TopHeadLinesAdapter;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TopHeadLinesFragment extends Fragment implements IView.ITopHeadLines, TopHeadLinesAdapter.ArticleItemClickListener {

    private RecyclerView recyclerView;
    private TopHeadLinesPresenter topHeadLinesPresenter;
    private String countrycode;
    private View newsLoader;
    private ArrayList<Article> articles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_head_lines, container, false);
        if(getArguments() != null){
             countrycode = getArguments().getString("countrycode");
        }
        recyclerView = view.findViewById(R.id.top_headlines_recycleView);
        newsLoader = view.findViewById(R.id.news_loading);
        newsLoader.setVisibility(View.VISIBLE);
        topHeadLinesPresenter = new TopHeadLinesPresenter(this, NewsApiClient.getNewsApiClient());
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
