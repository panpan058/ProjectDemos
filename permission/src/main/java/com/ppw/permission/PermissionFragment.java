package com.ppw.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/04/02
 *     desc   : 请求权限的Fragment
 *     version: 1.0   初始化
 *     params:
 *  <pre>
 */
public class PermissionFragment extends Fragment {
    private static final int PERMISSIONS_REQUEST_CODE = 510;
    private static final String PERMISSIONS = "permissions";
    private PermissionCallback mCallback;

    public static PermissionFragment newInstance (ArrayList permissions) {
        PermissionFragment fragment = new PermissionFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PERMISSIONS, permissions);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList permissions = getArguments().getStringArrayList("permissions");
        if ((permissions.contains(Manifest.permission.SYSTEM_ALERT_WINDOW) && ! PermissionUtils.isHasOverlaysPermission(getActivity()))) {

            //            if (permissions.contains(Manifest.permission.REQUEST_INSTALL_PACKAGES) && ! PermissionUtils
            // .isHasInstallPermission(getActivity())) {
            //                //跳转到允许安装未知来源设置页面
            //                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
            //                        Uri.parse("package:" + getActivity().getPackageName()));
            //                startActivityForResult(intent, PERMISSIONS_REQUEST_CODE);
            //            }

            if (permissions.contains(Manifest.permission.SYSTEM_ALERT_WINDOW) && ! PermissionUtils.isHasOverlaysPermission(getActivity())) {
                //跳转到悬浮窗设置页面
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getActivity().getPackageName()));
                startActivityForResult(intent, PERMISSIONS_REQUEST_CODE);
            }

        } else {
            String[] array = (String[]) permissions.toArray(new String[permissions.size()]);
            this.requestPermissions(array, PERMISSIONS_REQUEST_CODE);
        }
    }

    public void requestPermission (AppCompatActivity activity, PermissionCallback callback) {
        this.mCallback = callback;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("permission");
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        fragmentManager.beginTransaction().add(this,"permission").commit();

    }

    public void requestPermission (Fragment fragment, PermissionCallback callback) {
        this.mCallback = callback;
        fragment.getChildFragmentManager().beginTransaction().add(this, "permission").commit();

    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            boolean[] shouldShowRequestPermissionRationale = new boolean[permissions.length];

            for (int i = 0; i < permissions.length; ++ i) {
                shouldShowRequestPermissionRationale[i] = this.shouldShowRequestPermissionRationale(permissions[i]);
            }
            this.onRequestPermissionsResult(permissions, grantResults, shouldShowRequestPermissionRationale);
        }
    }

    private void onRequestPermissionsResult (String[] permissions, int[] grantResults,
                                             boolean[] shouldShowRequestPermissionRationale) {
        boolean hasPermission = true;
        boolean doNotAsk = false;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PermissionChecker.PERMISSION_DENIED) {
                hasPermission = false;
                if (! shouldShowRequestPermissionRationale[i]) {
                    doNotAsk = true;
                }
                break;
            }
        }
        //获取全部权限
        if (hasPermission ) {
            mCallback.hasPermission();
        } else {
            //没全部权限 或者dontask
            if (doNotAsk) {
                mCallback.doNotAsk();
            } else {
                mCallback.noPermission();
            }
        }
//        getActivity().getSupportFragmentManager().beginTransaction().hide(this).commit();
    }

    private boolean isBackCall;//是否已经回调了，避免安装权限和悬浮窗同时请求导致的重复回调

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (! isBackCall && requestCode == PERMISSIONS_REQUEST_CODE) {
            isBackCall = true;
            //需要延迟执行，不然有些华为机型授权了但是获取不到权限
            getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run () {
                    requestPermission();
                }
            }, 500);
        }
    }

    /**
     * 请求权限
     */
    public void requestPermission () {
        if (PermissionUtils.isSDK23()) {
            ArrayList<String> permissions = getArguments().getStringArrayList(PERMISSIONS);
            requestPermissions(permissions.toArray(new String[permissions.size()]), PERMISSIONS_REQUEST_CODE);
        }
    }
}
