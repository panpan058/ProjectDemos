package com.ppw.projectdemos.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ppw.projectdemos.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/3/19 13:33
 *     desc   : 启动页
 *     version: 1.0     初始化
 *     params:  key:        value:
 *  <pre>
 */
public class SplashActivity  extends AppCompatActivity {

    TextView cpActivitySplash;
    private int time = 4;
    private Handler mHandler;


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.AppTheme);
//        ScreenUtils.steepStatus(this);
        ImmersionBar.with(this).init();
        setContentView(initLayoutId());
        cpActivitySplash = findViewById(R.id.cp_activitySplash);
        initData();
        cpActivitySplash.setOnClickListener(v -> onViewClicked());
        Log.e("ppw SplashActivity", "onCreate: ");
    }


    protected int initLayoutId () {
        return R.layout.activity_splash;
    }

    @SuppressLint ("HandlerLeak")
    protected void initData () {
        mHandler = new Handler() {
            @Override
            public void handleMessage (Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        time--;
                        if (time == 0) {
                            mHandler.sendEmptyMessage(1);
                            cpActivitySplash.setText("跳过");
                        } else {
                            mHandler.sendEmptyMessageDelayed(0,1000);
                            cpActivitySplash.setText("剩余" + time + "秒");
                        }
                        break;
                    case 1:
                        onViewClicked();
                        break;
                }

            }
        };
        mHandler.sendEmptyMessage(0);
    }

    public void onViewClicked () {
        mHandler.removeMessages(0);
        mHandler.removeMessages(1);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeMessages(0);
            mHandler.removeMessages(1);
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
