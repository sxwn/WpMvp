package com.xiaowei.wpmvp.utils;

import android.widget.Toast;

import com.xiaowei.wpmvp.MyApp;

/**
 * 原生的接口有不妥之处，连续点击会连续土司
 */
public class ToastUtil {
    private static Toast toast;
    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(MyApp.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}