package com.balu.komalexclusivenews.android_concepts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.android_concepts.service.IntentSrvcResultReciever;
import com.balu.komalexclusivenews.android_concepts.service.MyIntentService;

public class IntentServiceActivity extends AppCompatActivity {

    private ImageView imageView;
    private BitmapBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        imageView = findViewById(R.id.img_download);
        setReceiver();
    }

    public void startIntentService(View view) {

        IntentSrvcResultReciever resultReciever = new IntentSrvcResultReciever(new Handler());
        resultReciever.setCallBack(new MyCallback());

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("receiverCallback", resultReciever);
        intent.setAction("Download");
        startService(intent);
    }


    private class MyCallback implements IntentSrvcResultReciever.IntentSrvcResultRecieverCallBack {
        @Override
        public void onError(int errorCode) {
            Toast.makeText(getApplicationContext(),"errorCode: "+errorCode,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(Bitmap data) {
            Toast.makeText(getApplicationContext(),"onSuccess: "+data,Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(data);
        }
    }

    public void setReceiver(){
         broadcastReceiver = new BitmapBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("MyBitMapAction");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,filter);

    }

    class BitmapBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra("MSG"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
