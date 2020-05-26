package com.balu.komalexclusivenews.firebase.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.R;
public class FirebaseFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firebase, container, false);
        view.findViewById(R.id.btn_analytics).setOnClickListener(this::onClick);
        view.findViewById(R.id.btn_auth).setOnClickListener(this::onClick);
        view.findViewById(R.id.btn_cloud_messaging).setOnClickListener(this::onClick);
        return view;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_auth:
                break;
            case R.id.btn_analytics:
                launchFragment(null,new Analyticsfragment());
                break;
            case R.id.btn_cloud_messaging:
                launchFragment(null,new FirebaseCloudMsgFragment());
                break;
            case R.id.btn_real_time_database:
                break;
        }
    }
    public void launchFragment(Bundle bundle, Fragment fragment){
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
