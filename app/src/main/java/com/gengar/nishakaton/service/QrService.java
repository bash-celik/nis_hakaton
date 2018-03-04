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
import android.widget.ImageButton;

import com.gengar.nishakaton.R;

public class QrService extends Service{

    private WindowManager manager;
    private View pop;

    public QrService(){}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        pop = LayoutInflater.from(this).inflate(R.layout.qr_popup, null);

        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //Specify the chat head position
        params.gravity = Gravity.CENTER;

        //Add the view to the window
        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.addView(pop, params);


        ImageButton close = pop.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf();
            }
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(pop != null)
            manager.removeView(pop);
    }
}
