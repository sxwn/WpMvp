package com.xiaowei.wpmvp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 1，做过哪方面的性能优化，我重点说了启动速度优化，卡顿优化
 * 2，内存泄露怎么产生的，如何解决
 * 3，Activity的启动流程
 * 4，如何实现类似京东app首页的效果
 * 5，MVCMVPMVVM的区别，还要比较详细的描述如何实现
 *
 * setContentView(); xml就开始解析了
 * xml解析过程
 * setContentView能不能使用LayoutInflater来替换？
 *
 * new textview  / 反射
 * listview recycleview viewpager gridview
 * 适配:
 * fragment
 * activity view window的关系?phonewindow decorview titleActionBar
 * setContent
 * 博客都对吗？对的---》学习博客解决问题的原理，解决问题的思路就是看源码，分析源码
 */

public class BannerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
    }
}
