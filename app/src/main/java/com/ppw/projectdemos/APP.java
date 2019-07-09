package com.ppw.projectdemos;

import android.app.Application;

/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/07/09
 *     desc   :
 *     version: 1.0   初始化
 *     params:
 *  <pre>
 */
public class APP extends Application {
    @Override
    public void onCreate () {
        super.onCreate();
        try {
            //停留2秒 查看启动状态
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
