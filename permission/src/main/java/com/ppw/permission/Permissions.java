package com.ppw.permission;

/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/04/15
 *     desc   :
 *     version: 1.0   初始化
 *     params:
 *  <pre>
 */

public class Permissions {
    private Permissions () {

    }

    private static class PermissionsHolder {
        private static final Permissions INSTANCE = new Permissions();
    }

    public static Permissions getInstance () {
        return PermissionsHolder.INSTANCE;
    }

}