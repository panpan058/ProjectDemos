package com.ppw.projectdemos.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ppw.projectdemos.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/4/17 17:16
 *     desc   : Glide加载圆角图片的问题
 *     version: 1.0     初始化
 *     params:  key:        value:
 *  <pre>
 */
public class GlideRoundActivity extends AppCompatActivity {
    private ImageView ivActivityGlideRound;
    private ImageView ivActivityGlideRoundCenterCrop;
    private ImageView ivActivityGlideRoundCenterCropResult;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_round);
        ivActivityGlideRound = (ImageView) findViewById(R.id.iv_activityGlideRound);
        ivActivityGlideRoundCenterCrop = (ImageView) findViewById(R.id.iv_activityGlideRoundCenterCrop);
        ivActivityGlideRoundCenterCropResult = (ImageView) findViewById(R.id.iv_activityGlideRoundCenterCropResult);
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
        roundOptions.transform(new CenterCrop(), new RoundedCorners(30));//处理CenterCrop的情况
        //加载第三个图片
        Glide.with(this)
                .load(R.mipmap.ic_test)
                .apply(roundOptions)
                .into(ivActivityGlideRoundCenterCropResult);
    }
}
