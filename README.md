# ProjectDemos
包含很多自己写的demo的项目
#  1.自定义对比条
![对比条图片](https://github.com/panpan058/ProjectDemos/raw/master/app/src/main/assets/comparedView.gif)
#  2.封装动态请求权限
    implementation 'com.github.panpan058:ProjectDemos:1.0'
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