package com.balu.komalexclusivenews.view.covid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.model.covid.Country;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CovidWorldSummaryAdapter extends RecyclerView.Adapter<CovidWorldSummaryAdapter.CovidSummaryViewHolder> {
    private final Context context;
    private final List<Country> countryList;

    public CovidWorldSummaryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CovidSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.covid_world_summary_item_row, parent,false);
        return new CovidSummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidSummaryViewHolder holder, int position) {
        holder.tv_location.setText(countryList.get(position).getCountry());
        holder.tv_new_confirmed.setText(countryList.get(position).getNewConfirmed()+"");
        holder.tv_total_confirmed.setText(countryList.get(position).getTotalConfirmed()+"");
        holder.tv_new_recovered.setText(countryList.get(position).getNewRecovered()+"");
        holder.tv_total_recovered.setText(countryList.get(position).getTotalRecovered()+"");
        holder.tv_new_deceased.setText(countryList.get(position).getNewDeaths()+"");
        holder.tv_total_deceased.setText(countryList.get(position).getTotalDeaths()+"");
        if(countryList.get(position).getNewConfirmed() == 0)
            holder.tv_new_confirmed.setVisibility(View.INVISIBLE);
        if(countryList.get(position).getNewRecovered() == 0)
            holder.tv_new_recovered.setVisibility(View.INVISIBLE);
        if(countryList.get(position).getNewDeaths() == 0)
            holder.tv_new_deceased.setVisibility(View.INVISIBLE);


    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CovidSummaryViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_location, tv_new_confirmed, tv_total_confirmed, tv_new_recovered, tv_total_recovered, tv_new_deceased, tv_total_deceased;
        public CovidSummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_location = itemView.findViewById(R.id.tv_location_covidWorld);
            tv_new_confirmed = itemView.findViewById(R.id.tv_new_confirmed_covidWorld);
            tv_total_confirmed = itemView.findViewById(R.id.tv_total_confirmed_covidWorld);
            tv_new_recovered = itemView.findViewById(R.id.tv_new_recovered_covidWorld);
            tv_total_recovered = itemView.findViewById(R.id.tv_total_recovered_covidWorld);
            tv_new_deceased = itemView.findViewById(R.id.tv_new_deceased_covidWorld);
            tv_total_deceased = itemView.findViewById(R.id.tv_total_deceased_covidWorld);
        }
    }
}
