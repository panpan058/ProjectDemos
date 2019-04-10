package com.ppw.projectdemos.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ppw.permission.PPWPermissions;
import com.ppw.permission.PermissionCallback;
import com.ppw.permission.PermissionUtils;
import com.ppw.projectdemos.R;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPermissionActivity extends AppCompatActivity {
    String TAG = "ActivityPermissionActivity";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    public void permission (View view) {
        PPWPermissions.with(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .permissions(Manifest.permission.SYSTEM_ALERT_WINDOW)
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
}
