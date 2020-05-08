package com.balu.komalexclusivenews.view.cricket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.model.cricket.Datum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchCalendarAdapter extends RecyclerView.Adapter<MatchCalendarAdapter.MatchCalendarVH> {
    private Context mContext;
    private List<Datum> datumList;

    public MatchCalendarAdapter(Context context,List<Datum> datumList) {
        mContext = context;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public MatchCalendarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.match_calendar_item_row, parent,false);
        return new MatchCalendarVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchCalendarVH holder, int position) {
        holder.name_tv.setText(datumList.get(position).getName());
        holder.date_tv.setText(datumList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class MatchCalendarVH extends RecyclerView.ViewHolder{
        private  TextView name_tv, date_tv;


        public MatchCalendarVH(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.tv_name_matchCalendar);
            date_tv = itemView.findViewById(R.id.tv_date_matchCalendar);
        }
    }
}
