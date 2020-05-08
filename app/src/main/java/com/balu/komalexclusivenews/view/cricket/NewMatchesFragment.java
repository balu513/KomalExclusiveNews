package com.balu.komalexclusivenews.view.cricket;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.databinding.FragmentNewMatchesBinding;
import com.balu.komalexclusivenews.model.cricket.Match;
import com.balu.komalexclusivenews.model.cricket.NewMatches;
import com.balu.komalexclusivenews.view.Adapter.NewMatchesAdapter;
import com.balu.komalexclusivenews.viewmodel.cricket.NewMatchesViewModel;

import java.util.ArrayList;
import java.util.List;


public class NewMatchesFragment extends Fragment {

    private NewMatchesViewModel matchesViewModel1;
    private List<Match> matches = new ArrayList<Match>();
    private NewMatchesAdapter newMatchesAdapter;
    FragmentNewMatchesBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matchesViewModel1 = ViewModelProviders.of(this).get(NewMatchesViewModel.class);
//        initViewModel();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_matches, container, false);
        binding.setViewmodel(matchesViewModel1);
        initView();
        matchesViewModel1.loadNewMatches();
        initLiveDataNewMatchesObserves();
        return binding.getRoot();
    }

    private void initLiveDataNewMatchesObserves(){
        observeNewMatchesSuccessResponse();
        observeNewMatchesFailureResponse();
    }
    private void initView() {
        binding.rvNewMatchesRecycleView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        newMatchesAdapter = new NewMatchesAdapter(getActivity(), matches);
        binding.rvNewMatchesRecycleView.setAdapter(newMatchesAdapter);
        if (binding.newMatchesLoading != null) {
            binding.newMatchesLoading.setVisibility(View.VISIBLE);
        }
    }

    public void observeNewMatchesSuccessResponse() {
        matchesViewModel1.getSuccessNewMatchesResponseLiveDate().observe(getActivity(), new Observer<NewMatches>() {
            @Override
            public void onChanged(NewMatches newMatches) {
                if (binding != null && binding.newMatchesLoading != null) {
                    binding.newMatchesLoading.setVisibility(View.GONE);
                }
                if (newMatches != null) {
                    Log.d("Success", newMatches.toString());
                    refreshMatchesList(newMatches.getMatches());
                }
            }
        });
    }

    private void refreshMatchesList(List<Match> newMatches) {
        matches.clear();
        matches.addAll(newMatches);
        if (newMatchesAdapter != null)
            newMatchesAdapter.notifyDataSetChanged();

    }

    public void observeNewMatchesFailureResponse() {
        matchesViewModel1.getFailureNewMatchesResponseLiveDate().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if (binding != null && binding.newMatchesLoading != null) {
                    binding.newMatchesLoading.setVisibility(View.GONE);
                }
                if (error != null) {
                    Log.d("Failure", error);
                }
            }
        });
    }
}
