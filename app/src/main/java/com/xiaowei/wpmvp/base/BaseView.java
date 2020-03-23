package com.xiaowei.wpmvp.base;

/**
 * 这些方法最终会交给你的视图（也就是 Activity 或者 Fragment）去实现
 */
public interface BaseView {
    /**
     * 显示对话框
     */
    void showLoading();

    /**
     * 隐藏对话框
     */
    void hideLoading();

    /**
     * 显示加载失败的内容
     */
    void showError();
}
