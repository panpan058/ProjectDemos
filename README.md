# ProjectDemos
包含很多自己写的demo的项目
#  对比条
![对比条图片](https://github.com/panpan058/ProjectDemos/raw/master/app/src/main/assets/comparedView.gif)
#   动态请求权限
##  使用
    PPWPermissions.with(this)   
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)    
                .permissions(Manifest.permission.SYSTEM_ALERT_WINDOW)   
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