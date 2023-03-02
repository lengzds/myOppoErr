package com.baidu.myjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isShow = false;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSplash(this);
    }

    public void showSplash(Activity activity) {
        if (activity != null) {
            FrameLayout splash = new FrameLayout(activity);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            splash.setLayoutParams(layoutParams);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ViewGroup viewGroup = activity.findViewById(R.id.splash_container);
            if (viewGroup != null) {
                viewGroup.setVisibility(View.VISIBLE);
                viewGroup.addView(splash);
            }
            splash.setBackgroundResource(R.drawable.pic_splash_logo);
            // 点击跳过
            splash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeSplashView();
                }
            });
            isShow = true;

            // 正常超时跳过
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    removeSplashView();
                }
            }, 10 * 1000);
        }
    }


    private void removeSplashView() {
        if (!isShow) {
            return;
        }
//        Toast.makeText(this, "removeSplashView", Toast.LENGTH_LONG).show();
        android.util.Log.e("test", "removeSplashView");
        ViewGroup view = this.findViewById(R.id.splash_container);
        if (view != null) {
            view.setVisibility(View.GONE);
            view.removeAllViews();
        }
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        isShow = false;
    }

}