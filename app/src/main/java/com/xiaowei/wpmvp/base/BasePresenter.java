package com.xiaowei.wpmvp.base;

import java.lang.ref.WeakReference;

/**
 * 1、首先声明了两个泛型 M 和 V，M 对应要处理的 Model，V 则对应负责展示的View
 * 2、V 一般比较大，这里采用了弱引用的写法，避免内存泄
 * @param <M>
 * @param <V>
 */

public abstract class BasePresenter<M,V> {
    protected M mModel;
    protected WeakReference<V> mViewRef;

    protected void onAttach(M model, V view) {
        mModel = model;
        mViewRef = new WeakReference<>(view);
    }

    protected  V getView() {
        return isViewAttached() ? mViewRef.get() : null;
    }

    /**
     * 用于检测 V 是否已关联 P，为真则让 getView() 返回对应的 V，否则返回 null
     * @return
     */
    protected boolean isViewAttached() {
        return  null != mViewRef && null!= mViewRef.get();
    }

    protected void onDetach() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
