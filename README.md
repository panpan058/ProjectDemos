# ProjectDemos
包含很多自己写的demo的项目
#  1.自定义对比条
![对比条图片](https://github.com/panpan058/ProjectDemos/raw/master/app/src/main/assets/comparedView.gif)
#  2.封装动态请求权限
    AndroidX:
    implementation 'com.pandaWang:pandaPermissionX:1.0'
    Support:
    implementation 'com.pandaWang:pandaPermission:1.0'

##  使用
    PPWPermissions.with(this)   
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)    
                .permissions(Manifest.permission.SYSTEM_ALERT_WINDOW)   
                .isContinue(true)        
                .request(new PermissionCallback() { 
                    @Override   
                    public void hasPermission () {  
                        Log.e(TAG, "hasPermission: 获取全部权限");        
                        Toast.makeText(getContext(), "hasPermission: 获取全部权限", Toast.LENGTH_SHORT).show();   
                    }   
                    @Override       
                    public void noPermission () {       
                        Log.e(TAG, "noPermission: 没有获取全部权限");       
                        Toast.makeText(getContext(), "noPermission: 没有获取全部权限", Toast.LENGTH_SHORT).show();          
                    }   
                    @Override       
                    public void doNotAsk () {//勾选了不再询问 跳到设置页面       
                        PermissionUtils.gotoPermissionSettings(getContext());       
                    }       
                }); 
    遗留的问题
    1:跳转到设置页面后,设置完以后不走回调的问题        
    2:设置悬浮窗权限后,回调里直接请求权限还是没有权限的问题   
    不是解决方法的方法:
        请求权限一定是要按钮点击触发,然后可以避免此类问题的发生    
#  3.Glide加载圆角图片不显示的问题
![Glide加载圆角图片](https://github.com/panpan058/ProjectDemos/raw/master/app/src/main/assets/glideRound.png)     
            
          RequestOptions roundOptions = new RequestOptions()    
                    .transform(new RoundedCorners(30));     
            //加载第一个图片       
            Glide.with(this)        
                    .load(R.mipmap.ic_test)     
                    .apply(roundOptions)        
                    .into(ivActivityGlideRound);        
            //加载第二个图片       
            Glide.with(this)        
                    .load(R.mipmap.ic_test)         
                    .apply(roundOptions)            
                    .into(ivActivityGlideRoundCenterCrop);          
            roundOptions.transform(new CenterCrop(), new RoundedCorners(30));//处理CenterCrop的情况 解决方法     
            //加载第三个图片       
            Glide.with(this)        
                    .load(R.mipmap.ic_test)     
                    .apply(roundOptions)        
                    .into(ivActivityGlideRoundCenterCropResult);  
              
#   4.TextView后面有图标,不满一行的时候图标跟着文本,满一行的时候图标在最右侧,文本显示...  
![TextView布局](https://img-blog.csdnimg.cn/20190625111136520.gif)    
 
        <?xml version="1.0" encoding="utf-8"?>
        <androidx.constraintlayout.widget.ConstraintLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".MainActivity">
        
            <TextView
                android:id="@+id/tv1"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="20dp"
                android:layout_marginTop="20dp"
                android:background="@color/colorPrimaryDark"
                android:text="左边"
                android:textColor="#fff"
                app:layout_constraintLeft_toLeftOf="parent"
                app:layout_constraintTop_toTopOf="parent"/>
        
            <TextView
                android:id="@+id/tv2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:singleLine="true"
                android:text="这是测试内容"
                app:layout_constrainedWidth="true"
                app:layout_constraintBottom_toBottomOf="@+id/tv1"
                app:layout_constraintHorizontal_bias="0"
                app:layout_constraintLeft_toRightOf="@+id/tv1"
                app:layout_constraintRight_toLeftOf="@+id/tv3"
                app:layout_constraintTop_toTopOf="@+id/tv1"
                app:layout_constraintHorizontal_chainStyle="packed"/>
        
            <TextView
                android:id="@+id/tv3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="20dp"
                android:layout_marginRight="20dp"
                android:background="@color/colorPrimaryDark"
                android:text="右边"
                android:textColor="#fff"
                app:layout_constraintLeft_toRightOf="@+id/tv2"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintTop_toTopOf="parent"/>
            <Button
                android:text="增加内容"
                app:layout_constraintLeft_toLeftOf="parent"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                android:onClick="addText"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
        </androidx.constraintlayout.widget.ConstraintLayout>  