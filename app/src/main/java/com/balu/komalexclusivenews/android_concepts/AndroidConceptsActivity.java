package com.balu.komalexclusivenews.android_concepts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.generated.callback.OnClickListener;

public class AndroidConceptsActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_concepts);
        findViewById(R.id.work_manager_btn).setOnClickListener(this);
        findViewById(R.id.intent_service_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.work_manager_btn:
                startActivity(new Intent(this, WorkManagerActivity.class));
                break;
            case R.id.intent_service_btn:
                startActivity(new Intent(this, IntentServiceActivity.class));
                break;
            case R.id.start_service_btn:
                break;
            case R.id.bound_service_btn:
                break;
        }
    }

}
