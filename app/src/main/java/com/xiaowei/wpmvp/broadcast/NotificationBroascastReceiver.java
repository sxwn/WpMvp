package com.xiaowei.wpmvp.broadcast;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xiaowei.wpmvp.utils.NotificationUtil;

public class NotificationBroascastReceiver extends BroadcastReceiver {

    public static final String TYPE = "type";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int type = intent.getIntExtra(TYPE, -1);
        if (type != -1) {
            Log.e("weip","cacelNotification");
            NotificationUtil.getInstance(context).cacelNotification();
        }

        if (action.equals("notification_clicked")) {
            //处理点击事件
            Log.e("weip","notification_clicked");
        }

        if (action.equals("notification_cancelled")) {
            //处理滑动清除和点击删除事件
            Log.e("weip","notification_cancelled");
        }
    }
}
