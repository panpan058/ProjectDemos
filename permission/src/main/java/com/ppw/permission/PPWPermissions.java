package com.ppw.permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/04/02
 *     desc   :
 *     version: 1.0   初始化
 *     params:
 *  <pre>
 */
public class PPWPermissions {
    private AppCompatActivity mActivity;
    private List<String> permissions;
    private Fragment mFragment;
    private PPWPermissions (AppCompatActivity activity) {
        mActivity = activity;
        permissions = new ArrayList<>();
    }

    private PPWPermissions (Fragment fragment) {
        mFragment = fragment;
        permissions = new ArrayList<>();
    }
    public static PPWPermissions with (AppCompatActivity activity) {
        return new PPWPermissions(activity);
    }

    public static PPWPermissions with (Fragment fragment) {
        return new PPWPermissions(((AppCompatActivity) fragment.getActivity()));
    }

    public PPWPermissions permissions (String... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
        return this;
    }

    public void request (PermissionCallback callback) {
        ArrayList<String> requestPermissions = PermissionUtils.getRequestPermissions(mActivity, permissions);
        if (requestPermissions == null || requestPermissions.size() == 0) {
            callback.hasPermission();
            return;
        }
        if (PermissionUtils.isSDK23()) {
            //请求权限
            PermissionFragment permissionFragment =
                    PermissionFragment.newInstance(requestPermissions);
            permissionFragment.requestPermission(mActivity, callback);
        } else {
            //跳转到设置页面
            PermissionUtils.gotoPermissionSettings(mActivity);
        }
    }
}
