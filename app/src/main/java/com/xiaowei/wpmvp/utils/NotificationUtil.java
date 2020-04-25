package com.xiaowei.wpmvp.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.xiaowei.wpmvp.MainActivity;
import com.xiaowei.wpmvp.R;
import com.xiaowei.wpmvp.broadcast.NotificationBroascastReceiver;

import java.util.HashMap;
import java.util.Map;

/**
 * 通知工具类
 */
public class NotificationUtil {

    private NotificationManager mNotificationManager;
    private Map<Integer, Notification> mNotifications;
    private static NotificationUtil notificationUtil;
    private Context mContext;

    public NotificationUtil(Context context){
        mContext = context;
        // 获得通知系统服务
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        // 创建通知集合
        mNotifications = new HashMap<>();
    }

    public static NotificationUtil getInstance(Context context) {
        if (notificationUtil == null) {
            notificationUtil = new NotificationUtil(context);
        }
        return notificationUtil;
    }

    /**
     * 显示通知
     */
    public void showNotification() {
        Log.e("weip","showNotification");
        if (mNotifications.containsKey(1)){
            return;
        }
        // 创建通知对象
        Notification notification = new Notification();
        // 设置滚动文字
        notification.tickerText = "notification";
        // 设置显示时间
        notification.when = System.currentTimeMillis();
        // 设置图标
        notification.icon = R.drawable.ic_launcher_background;
        // 设置通知特性
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.priority = Notification.PRIORITY_MAX;
        // 设置点击通知栏操作
        Intent intent = new Intent(mContext, NotificationBroascastReceiver.class);
        intent.putExtra(NotificationBroascastReceiver.TYPE, 3);
        PendingIntent pIntent = PendingIntent.getBroadcast(mContext,0,intent,PendingIntent.FLAG_ONE_SHOT);
        notification.contentIntent = pIntent;
        // 创建RemoteViews对象
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),R.layout.notification);
        // 设置确定按钮操作
        Intent intentCommit = new Intent(mContext, NotificationBroascastReceiver.class);
        intentCommit.setAction("notification_clicked");
        intentCommit.putExtra(NotificationBroascastReceiver.TYPE, 1);
        PendingIntent piCommit = PendingIntent.getBroadcast(mContext,0,intentCommit,PendingIntent.FLAG_ONE_SHOT);
        remoteViews.setOnClickPendingIntent(R.id.notification_commit,piCommit);
        // 设置取消按钮操作
        Intent intentCancel = new Intent(mContext, NotificationBroascastReceiver.class);
        intentCancel.setAction("notification_cancelled");
        intentCancel.putExtra(NotificationBroascastReceiver.TYPE, 2);
        PendingIntent piCancel = PendingIntent.getBroadcast(mContext,0,intentCancel,PendingIntent.FLAG_ONE_SHOT);
        remoteViews.setOnClickPendingIntent(R.id.notification_cancel,piCancel);
        // 设置Notification的视图
        notification.contentView = remoteViews;
        notification.bigContentView = remoteViews;
        // 发出通知
        Log.e("weip","notify");
        mNotificationManager.notify(1,notification);
        //把通知添加到集合中
        mNotifications.put(1,notification);
    }

    /**
     * 取消通知
     */
    public void cacelNotification(){
        //取消通知
        mNotificationManager.cancel(1);
        mNotifications.remove(1);
    }

}
