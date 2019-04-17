package com.ppw.projectdemos.activity;

import android.os.Bundle;

import com.ppw.projectdemos.R;
import com.ppw.projectdemos.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
/**
 *  <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/4/17 17:15
 *     desc   : Fragment请求权限
 *     version: 1.0     初始化
 *     params:  key:        value:
 *  <pre>
 */
public class FragmentPermissionActivity extends AppCompatActivity {
    ViewPager mViewPager;
    List<Fragment> mFragments;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_permission);
        mViewPager = findViewById(R.id.vp);
        mFragments = new ArrayList<>();
        mFragments.add(new TestFragment());
        mFragments.add(new TestFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem (int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount () {
                return mFragments.size();
            }
        });
    }
}
