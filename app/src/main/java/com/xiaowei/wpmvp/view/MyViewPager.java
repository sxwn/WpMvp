package com.xiaowei.wpmvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 小米 ： pageview
 * 1、百度；
 * 2、StackOverflow  错误的版本是中国的
 * okhttp：5mins  使用方法
 * glide：30mins
 * viewpager wrap_content: 高度无法设置一定是度量出现问题了  无效?   onMeasure():源码;
 * 使用错了
 * fragment: getActivity null
 * attach  detach之内不为null
 * 非一个进程里面(推送,通知)  activity  mContext ==>  内存泄漏
 * 先确定自己===》再确定孩子
 * 修改heightMeasureSpec的值？
 * 栈溢出了？  递归函数导致
 */

public class MyViewPager extends ViewPager {

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 面向对象  多态

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
//            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            // 如何才有意义？
            ViewGroup.LayoutParams params = child.getLayoutParams();
            int heightSpec = getChildMeasureSpec(heightMeasureSpec,0,params.height);
            child.measure(widthMeasureSpec,heightSpec);
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
