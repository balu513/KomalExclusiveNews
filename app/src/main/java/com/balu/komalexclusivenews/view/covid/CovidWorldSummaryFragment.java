package com.balu.komalexclusivenews.view.covid;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.model.covid.Country;
import com.balu.komalexclusivenews.model.covid.CovidWorldSummary;
import com.balu.komalexclusivenews.model.covid.Global;
import com.balu.komalexclusivenews.viewmodel.covid.CovidWorldSummaryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CovidWorldSummaryFragment} factory method to
 * create an instance of this fragment.
 */
public class CovidWorldSummaryFragment extends Fragment {

    private CovidWorldSummaryViewModel covidWorldSummaryViewModel;
    private List<Country> countryList = new ArrayList<Country>();
    private RecyclerView recyclerView;
    private CovidWorldSummaryAdapter covidWorldSummaryAdapter;
    private TextView tv_newConfirmed, tv_totalConfirmed, tv_newRecovered, tv_totalRecovered, tv_newDeceased, tv_totalDeceased;

    public CovidWorldSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        covidWorldSummaryViewModel = ViewModelProviders.of(this).get(CovidWorldSummaryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_covid_world_summary, container, false);
        initView(view);

        covidWorldSummaryViewModel.loadCovidWorldSummary();
        observeCovidWorldSummaryFailureSummary();
        observeCovidWorldSummarySuccessSummary();

        return view;
    }
    private void initView(View view){
        view.findViewById(R.id.tv_confirmed_covidWorld).setVisibility(View.VISIBLE);
        view.findViewById(R.id.tv_recovered_covidWorld).setVisibility(View.VISIBLE);
        view.findViewById(R.id.tv_deceased_covidWorld).setVisibility(View.VISIBLE);
        tv_newConfirmed = view.findViewById(R.id.tv_new_confirmed_covidWorld);
        tv_totalConfirmed = view.findViewById(R.id.tv_total_confirmed_covidWorld);
        tv_newRecovered = view.findViewById(R.id.tv_new_recovered_covidWorld);
        tv_totalRecovered = view.findViewById(R.id.tv_total_recovered_covidWorld);
        tv_newDeceased = view.findViewById(R.id.tv_new_deceased_covidWorld);
        tv_totalDeceased = view.findViewById(R.id.tv_total_deceased_covidWorld);
        recyclerView = view.findViewById(R.id.covid_worldSummary_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        covidWorldSummaryAdapter = new CovidWorldSummaryAdapter(getActivity().getApplicationContext(), countryList);
        recyclerView.setAdapter(covidWorldSummaryAdapter);

    }

    public void observeCovidWorldSummarySuccessSummary(){
        covidWorldSummaryViewModel.getSuccessCovidWorldSummaryMutableLiveData().observe(getActivity(), new Observer<CovidWorldSummary>() {
            @Override
            public void onChanged(CovidWorldSummary covidWorldSummary) {
                if (covidWorldSummary != null){
                    countryList.clear();
                    countryList.addAll(covidWorldSummary.getCountries());
                    covidWorldSummaryAdapter.notifyDataSetChanged();
                    refreshGlobalUpdate(covidWorldSummary.getGlobal());
                }

            }
        });
    }
    public void observeCovidWorldSummaryFailureSummary(){
        covidWorldSummaryViewModel.getFailureCovidWorldSummaryMutableLiveData().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    Toast.makeText(getActivity(),"Failed" +s, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void refreshGlobalUpdate(Global global){
        tv_newConfirmed.setText(global.getNewConfirmed()+"");
        tv_totalConfirmed.setText(global.getTotalConfirmed()+"");
        tv_newRecovered.setText(global.getNewRecovered()+"");
        tv_totalRecovered.setText(global.getTotalRecovered()+"");
        tv_newDeceased.setText(global.getNewDeaths()+"");
        tv_totalDeceased.setText(global.getTotalDeaths()+"");
        if(global.getNewConfirmed() == 0)
            tv_newConfirmed.setVisibility(View.INVISIBLE);
        if(global.getNewRecovered() == 0)
            tv_newRecovered.setVisibility(View.INVISIBLE);
        if(global.getNewDeaths() == 0)
            tv_newDeceased.setVisibility(View.INVISIBLE);
    }
}
