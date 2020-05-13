package com.balu.komalexclusivenews.android_concepts.service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class IntentSrvcResultReciever extends ResultReceiver {
    private IntentSrvcResultRecieverCallBack intentSrvcResultRecieverCallBack;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public IntentSrvcResultReciever(Handler handler) {
        super(handler);
    }

    public void setCallBack(IntentSrvcResultRecieverCallBack IntentSrvcResultRecieverCallBack){
        intentSrvcResultRecieverCallBack = IntentSrvcResultRecieverCallBack;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if(intentSrvcResultRecieverCallBack !=null){
            if(resultCode == 100){
              Bitmap bitmap = resultData.getParcelable("downloadImage");
              intentSrvcResultRecieverCallBack.onSuccess(bitmap);
            }else{
             intentSrvcResultRecieverCallBack.onError(resultCode);
            }
        }
    }

    public interface IntentSrvcResultRecieverCallBack{
        public void onError(int errorCode);
        public void onSuccess(Bitmap data);
    }
}
