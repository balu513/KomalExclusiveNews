package com.balu.komalexclusivenews.mvp.model.news;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopHeadlines implements Parcelable {

@SerializedName("status")
@Expose
private String status;
@SerializedName("totalResults")
@Expose
private Integer totalResults;
@SerializedName("articles")
@Expose
private List<Article> articles = null;

    protected TopHeadlines(Parcel in) {
        status = in.readString();
        if (in.readByte() == 0) {
            totalResults = null;
        } else {
            totalResults = in.readInt();
        }
        articles = in.createTypedArrayList(Article.CREATOR);
    }

    public static final Creator<TopHeadlines> CREATOR = new Creator<TopHeadlines>() {
        @Override
        public TopHeadlines createFromParcel(Parcel in) {
            return new TopHeadlines(in);
        }

        @Override
        public TopHeadlines[] newArray(int size) {
            return new TopHeadlines[size];
        }
    };

    public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Integer getTotalResults() {
return totalResults;
}

public void setTotalResults(Integer totalResults) {
this.totalResults = totalResults;
}

public List<Article> getArticles() {
return articles;
}

public void setArticles(List<Article> articles) {
this.articles = articles;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        if (totalResults == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalResults);
        }
        dest.writeTypedList(articles);
    }
}