package com.balu.komalexclusivenews.android_concepts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.android_concepts.service.MyWorker;

import java.util.concurrent.TimeUnit;

public class WorkManagerActivity extends AppCompatActivity {

    private OneTimeWorkRequest workRequest;
    private PeriodicWorkRequest periodicWorkRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);

        handleOneTimeWorkRequest();
        handlePeriodicWorkRequest();

        handleWorkInfoListener();


    }

    public void handleOneTimeWorkRequest(){
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true).build();
        Data data = new Data.Builder()
                .putString("DESC", "Task data from OneTimeWorkrequest")
                .build();
        workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();
    }

    public void handleWorkInfoListener(){
        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if(workInfo != null && workInfo.getState().isFinished()){
                            Toast.makeText(WorkManagerActivity.this, workInfo.getOutputData().getString("DESC"), Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(WorkManagerActivity.this, "Status " + workInfo.getState(), Toast.LENGTH_SHORT).show();
                    }
                });
        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if(workInfo != null && workInfo.getState().isFinished()){
                            Toast.makeText(WorkManagerActivity.this, workInfo.getOutputData().getString("DESC"), Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(WorkManagerActivity.this, "Status " + workInfo.getState(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void handlePeriodicWorkRequest(){
        Data data = new Data.Builder().putString("DESC", "Task data from PeriodicWorkRequest").build();
        periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 16, TimeUnit.MINUTES).build();

    }


    public void ClickListener(View view) {
        switch (view.getId()){
            case R.id.btn_enqueue_request:
                WorkManager.getInstance().enqueue(workRequest);
                WorkManager.getInstance().enqueue(periodicWorkRequest);
                break;
            case R.id.btn_cancel_request:
                WorkManager.getInstance().cancelWorkById(workRequest.getId());
                break;
        }
        WorkManager.getInstance().enqueue(workRequest);

    }
}

