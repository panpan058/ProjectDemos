package com.ppw.projectdemos.fragment;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ppw.permission.PPWPermissions;
import com.ppw.permission.PermissionCallback;
import com.ppw.permission.PermissionUtils;
import com.ppw.projectdemos.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/04/09
 *     desc   :
 *     version: 1.0   初始化
 *     params:
 *  <pre>
 */
public class TestFragment extends Fragment {
    private String TAG = "TestFragment";

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_permission, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_fragmentPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                requestPermission();
            }
        });
    }

    private void requestPermission () {
        PPWPermissions.getInstance()
                .with(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .permissions(Manifest.permission.ACCESS_COARSE_LOCATION)
                .request(new PermissionCallback() {
                    @Override
                    public void hasPermission () {
                        Log.e(TAG, "hasPermission: 获取全部权限");
                        Toast.makeText(getContext(), "hasPermission: 获取全部权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void noPermission () {
                        Log.e(TAG, "noPermission: 没有权限");
                        Toast.makeText(getContext(), "noPermission: 没有权限", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void doNotAsk () {
                        PermissionUtils.gotoPermissionSettings(getContext());
                    }
                });
    }
}
