package com.gengar.nishakaton.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.gengar.nishakaton.R;

public class PopUpService extends Service {

    private WindowManager manager;
    private View popup;

    public PopUpService(){}


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        popup = LayoutInflater.from(this)
                .inflate(R.layout.pop_up,null);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        params.gravity = Gravity.CENTER;

        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.addView(popup,params);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(popup != null)
            manager.removeView(popup);
    }
}
