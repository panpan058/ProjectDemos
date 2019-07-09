package com.ppw.projectdemos.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ppw.permission.PPWPermissions;
import com.ppw.permission.PermissionCallback;
import com.ppw.permission.PermissionUtils;
import com.ppw.projectdemos.R;

import androidx.appcompat.app.AppCompatActivity;
/**
 *  <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/4/17 17:16
 *     desc   : Activity请求权限
 *     version: 1.0     初始化
 *     params:  key:        value:
 *  <pre>
 */
public class ActivityPermissionActivity extends AppCompatActivity {
    String TAG = "ActivityPermissionActivity";
    boolean isContinue;
    TextView mTextView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mTextView = findViewById(R.id.tv);
        String text = "isContinue是在碰见悬浮窗的情况下,请求完悬浮窗的权限后,无论是否有次权限都还继续请求剩余的权限" +
                "\n 1.True的情况 无论悬浮窗是否有权限 后续权限都会请求" +
                "\n 2.False的请求 只有悬浮窗有权限了以后 后续权限才会继续请求";
        mTextView.setText(text);
    }

    public void permission () {
        PPWPermissions.getInstance()
                .with(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .permissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .isContinue(isContinue)
                .request(new PermissionCallback() {
                    @Override
                    public void hasPermission () {
                        Log.e(TAG, "hasPermission: 获取全部权限");
                        Toast.makeText(getApplicationContext(), "hasPermission: 获取全部权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void noPermission () {
                        Log.e(TAG, "noPermission: 没有权限");
                        Toast.makeText(getApplicationContext(), "noPermission: 没有权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void doNotAsk () {
                        PermissionUtils.gotoPermissionSettings(ActivityPermissionActivity.this);
                    }
                });
    }

    public void permissionNotContinue (View view) {
        isContinue = false;
        permission();
    }

    public void permissionContinue (View view) {
        isContinue = true;
        permission();
    }
}
