package com.balu.komalexclusivenews.view.cricket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.model.cricket.Datum;
import com.balu.komalexclusivenews.model.cricket.MatchCalendar;
import com.balu.komalexclusivenews.view.Adapter.NewMatchesAdapter;
import com.balu.komalexclusivenews.viewmodel.cricket.MatchCalendarViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchCalendarFragment} factory method to
 * create an instance of this fragment.
 */
public class MatchCalendarFragment extends Fragment {
    private MatchCalendarViewModel matchCalendarViewModel;
    private RecyclerView rvMatchCalendarRecycleView;
    private List<Datum> matchCalendarList = new ArrayList<Datum>();
    private MatchCalendarAdapter matchCalendarAdapter;

    public MatchCalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         matchCalendarViewModel = ViewModelProviders.of(this).get(MatchCalendarViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_calendar, container, false);
        rvMatchCalendarRecycleView = view.findViewById(R.id.rv_match_calendar_recycleView);
        initView();
        matchCalendarViewModel.loadMatchCalendar();
        observeMatchCalendarFailureResponse();
        observeMatchCalendarSuccessResponse();

        return view;
    }
    private void initView() {
        rvMatchCalendarRecycleView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        matchCalendarAdapter = new MatchCalendarAdapter(getActivity().getApplicationContext(), matchCalendarList);
        rvMatchCalendarRecycleView.setAdapter(matchCalendarAdapter);
    }
    public void observeMatchCalendarSuccessResponse(){
        matchCalendarViewModel.getSuccessMatchCalendarMutableLiveData().observe(getActivity(), new Observer<MatchCalendar>() {
            @Override
            public void onChanged(MatchCalendar matchCalendar) {
                matchCalendarList.clear();
                matchCalendarList.addAll(matchCalendar.getData());
                matchCalendarAdapter.notifyDataSetChanged();
            }
        });
    }
    public void observeMatchCalendarFailureResponse(){
        matchCalendarViewModel.getFailureMatchCalendarMutableLiveData().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("Match calendar", s.toString());
            }
        });
    }
}
