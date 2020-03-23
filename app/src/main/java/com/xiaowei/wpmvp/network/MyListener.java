package com.xiaowei.wpmvp.network;

/**
 * 这是一个回调，成功时携带泛型描述的 Java 对象，失败时则携带错误信息
 * @param <T>
 */
public interface MyListener<T>{
    void onSuccess(T result);
    void onError(String errorMsg);
}
