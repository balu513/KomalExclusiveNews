package com.balu.komalexclusivenews.view.cricket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.mvp.view.MainActivity;


public class CricHomeFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cricket_home, container, false);
        initOnclickListeners(view);
        return view;

    }

    private void initOnclickListeners(View view) {
        view.findViewById(R.id.card_new_mathces).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).launchFragment(null, new NewMatchesFragment());
            }
        });
        view.findViewById(R.id.card_match_calendar).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).launchFragment(null, new MatchCalendarFragment());
            }
        });
    }
}
