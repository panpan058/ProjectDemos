package com.ppw.permission;

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
public interface PermissionCallback {

    /**
     * 获取到全部权限
     */
    void hasPermission ();


    /**
     * 没有获取到权限
     */
    void noPermission ();

    /**
     * 打上不在询问对号的
     */
    void doNotAsk ();
}
