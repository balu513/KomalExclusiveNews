package com.balu.komalexclusivenews.view.covid;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CovidWorldSummaryFragment} factory method to
 * create an instance of this fragment.
 */
public class CovidWorldSummaryFragment extends Fragment {

    public CovidWorldSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_covid_world_summary, container, false);

        return view;
    }
}
