package com.xiaowei.wpmvp.base;

public interface MvpListener<T> {
    void onSuccess(T result);
    void onError(String errorMsg);
}
