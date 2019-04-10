package com.ppw.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

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
public class PermissionUtils {
    /**
     * 判断是否是6.0以上版本
     *
     * @return
     */
    static boolean isSDK23 () {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
    /**
     * 是否是8.0以上版本
     */
    static boolean isSDK26 () {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }
    /** 获取需要请求的权限
     * @param context
     * @param permissions
     * @return
     */
    static ArrayList<String> getRequestPermissions (Context context, List<String> permissions) {
        //如果是6.0版本一下 不用请求
        if (! isSDK23()) {
            return null;
        }
        //获取没有获取的权限
        ArrayList<String> result = new ArrayList<>();
        for (String permission : permissions) {
            if (permission.equals(Manifest.permission.REQUEST_INSTALL_PACKAGES)) {

                if (!isHasInstallPermission(context)) {
                    result.add(permission);
                }
                continue;
            }

            //检测悬浮窗权限
            if (permission.equals(Manifest.permission.SYSTEM_ALERT_WINDOW)) {

                if (!isHasOverlaysPermission(context)) {
                    result.add(permission);
                }
                continue;
            }

            if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                result.add(permission);
            }
        }
        return result;
    }

    /**
     * 是否有安装权限
     */
    static boolean isHasInstallPermission(Context context) {
        if (isSDK26()) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }
    /**
     * 是否有悬浮窗权限
     */
    static boolean isHasOverlaysPermission(Context context) {
        if (isSDK23()) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    /**
     * 跳转到应用权限设置页面
     *
     * @param context 上下文对象
     */
    public static void gotoPermissionSettings(Context context) {
        PermissionSettingPage.start(context, false);
    }

}
