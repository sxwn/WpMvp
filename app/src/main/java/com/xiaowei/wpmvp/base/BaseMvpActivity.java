package com.xiaowei.wpmvp.base;

import android.os.Bundle;

import com.xiaowei.wpmvp.utils.ReflectUtil;

/**
 * 遇到动态的，有数据请求和处理的页面，再让 MVP 出马。
 * 这个 BaseMvpActivity 继承了 BaseActivity，因此包含了里面全部功能，同时又添加了一个抽象方法 loadData()，
 * 有关数据交互的方法写在里面即可。
 * @param <T>
 * @param <M>
 */

public abstract class BaseMvpActivity<T extends BasePresenter, M extends BaseModel> extends BaseActivity {

    protected T mPresenter;
    protected M mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ReflectUtil.getT(this, 0);
        mModel = ReflectUtil.getT(this, 1);
        mPresenter.onAttach(mModel, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    protected abstract void loadData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

}
