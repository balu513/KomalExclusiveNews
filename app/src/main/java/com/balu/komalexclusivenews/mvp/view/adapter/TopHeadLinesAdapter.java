package com.balu.komalexclusivenews.mvp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.mvp.model.news.Article;
import com.balu.komalexclusivenews.mvp.model.news.TopHeadlines;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopHeadLinesAdapter extends RecyclerView.Adapter<TopHeadLinesAdapter.TopHeadLinesViewHolder> {


    private  List<Article> articlesList;
    private ArticleItemClickListener mArticleItemClickListener;
    private Context context;
    private TopHeadlines topHeadlines;

    public TopHeadLinesAdapter(Context context, List<Article> articlesList, ArticleItemClickListener articleItemClickListener) {
        this.context = context;
        this.articlesList = articlesList;
        this.mArticleItemClickListener = articleItemClickListener;
    }

    @NonNull
    @Override
    public TopHeadLinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top_headlines_item_row,parent,false);
        return new TopHeadLinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopHeadLinesViewHolder holder, int position) {
        Article article = articlesList.get(position);
        holder.title_tv.setText(article.getTitle());
        Glide.with(context).load(article.getUrlToImage()).placeholder(R.drawable.news_default)
                .into(holder.img_iv);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class TopHeadLinesViewHolder  extends RecyclerView.ViewHolder {
        private final TextView title_tv;
        private final ImageView img_iv;

        public TopHeadLinesViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_topheadines_tv);
            img_iv = itemView.findViewById(R.id.img_topheadlines_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mArticleItemClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
    public interface ArticleItemClickListener{
        void onClick( int position);
    }
}
