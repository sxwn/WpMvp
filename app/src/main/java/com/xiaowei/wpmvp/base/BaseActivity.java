package com.xiaowei.wpmvp.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaowei.wpmvp.utils.ToastUtil;

/**
 * 有时我们的活动只是一个静态的容器（比如欢迎页），这时其实是没必要使用 MVP 的。所以把包括 UI 的逻辑（双击退出）封装在此。
 * BaseView 里面的方法也在此重写，简明起见，就不具体实现了。
 * 如果你用了 ButterKnife、Dagger 等依赖注入框架，初始化和解绑（去 onDestory() 方法）工作同样可以在这个 BaseActivity 里完成
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    //双击退出相关
    private boolean mFlag = false;
    private long mTimeout = -1;

    private boolean checkBackAction() {
        long time = 3000L;//判定时间设为3秒
        boolean flag = mFlag;
        mFlag = true;
        boolean timeout = (mTimeout == -1 || (System.currentTimeMillis() - mTimeout) > time);
        if (mFlag && (mFlag != flag || timeout)) {
            mTimeout = System.currentTimeMillis();
            ToastUtil.showToast("再点击一次回到桌面");
            return true;
        }
        return !mFlag;
    }
}
