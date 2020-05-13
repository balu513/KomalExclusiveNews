package com.balu.komalexclusivenews.android_concepts.service;


import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;

import com.balu.komalexclusivenews.android_concepts.IntentServiceActivity;
import com.balu.komalexclusivenews.mvp.view.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyIntentService extends IntentService {

    private String imageUrl = "https://cbsnews2.cbsistatic.com/hub/i/r/2020/05/10/77eb87e9-b2c8-4ddb-9170-0509904c821a/thumbnail/1200x630/39643ba7a12673c0cd46ce080f1b26ff/2020-05-10t183350z-60266177-rc2ulg9zqe6i-rtrmadp-3-health-coronavirus-britain-johnson.jpg";
    private ResultReceiver resultReciever;


    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService(){
        super("");

    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            resultReciever = intent.getParcelableExtra("receiverCallback");
        }

        if (intent.getAction() != null && intent.getAction().equals("Download")) {
            Bundle resultBundle = new Bundle();
            int resultCode = 0;
            try {
                Bitmap bitmap = downloadBitmapFrpomUrl(imageUrl);
                resultBundle.putParcelable("downloadImage",bitmap);
                resultCode = 100;
            } catch (Exception e) {
               resultCode = 9999;
            }
            resultReciever.send(resultCode,resultBundle);
        }
        intent.setAction("MyBitMapAction");
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("MSG", "From onHandleIntent Download successfull"));

    }


    public Bitmap downloadBitmapFrpomUrl(String src) throws Exception {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
    }
}
