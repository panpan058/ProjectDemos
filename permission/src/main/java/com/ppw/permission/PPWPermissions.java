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
    private List<String> permissions;//请求的权限
    private boolean isContinue;//碰见悬浮窗权限是否是继续请求权限

    private PPWPermissions () {

    }


    private static class PPWPermissionsHolder {
        private static final PPWPermissions INSTANCE = new PPWPermissions();
    }

    public static PPWPermissions getInstance () {
        return PPWPermissionsHolder.INSTANCE;
    }

    public PPWPermissions with (AppCompatActivity activity) {
        mActivity = activity;
        permissions = new ArrayList<>();
        return this;
    }

    public PPWPermissions with (Fragment fragment) {
        mActivity = ((AppCompatActivity) fragment.getActivity());
        permissions = new ArrayList<>();
        return this;
    }

    public PPWPermissions permissions (String... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
        return this;
    }

    public PPWPermissions isContinue (boolean isContinue) {
        this.isContinue = isContinue;
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
                    PermissionFragment.newInstance(requestPermissions, isContinue);
            permissionFragment.requestPermission(mActivity, callback);
        } else {
            //跳转到设置页面
            PermissionUtils.gotoPermissionSettings(mActivity);
        }
    }
}
