package com.gengar.nishakaton.service;

import android.app.FragmentManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.gengar.nishakaton.MainActivity;
import com.gengar.nishakaton.R;
import com.gengar.nishakaton.RecyclerAdapter;
import com.gengar.nishakaton.network.ApiUtill;
import com.gengar.nishakaton.pojo.PostPump;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopUpService extends Service {

    private WindowManager manager;
    private View popup;
    private RecyclerView recyclerView;

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

        ImageButton close = popup.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSelf();
            }
        });

        recyclerView = popup.findViewById(R.id.recycler);


        goGetOffer();


    }


    private void goGetOffer(){
        Log.e("STARTED","STARTED");
        ApiUtill.getApi().postPump("123","321").enqueue(new Callback<List<PostPump>>() {
            @Override
            public void onResponse(Call<List<PostPump>> call, Response<List<PostPump>> response) {
                if(response.isSuccessful()){
                    Log.e("RESPONSE","SUCESS");

                    RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(),response.body());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);



                }else {
                    Log.e("RESPONSE","unSUCESS");
                }
            }

            @Override
            public void onFailure(Call<List<PostPump>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(popup != null)
            manager.removeView(popup);
    }
}
