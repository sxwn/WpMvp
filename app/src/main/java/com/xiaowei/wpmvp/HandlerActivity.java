package com.xiaowei.wpmvp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.SoftReference;

public class HandlerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 内存泄漏：当一个对象在java堆中应该被回收，却没有被回收的这个对象被称为内存泄漏
        handler.sendEmptyMessageDelayed(handle_flag,1000);
    }

    private static int handle_flag = 666;

    // 持有activity的引用，内部类，持有外部类的引用
    //处理handler内存泄漏的解决方案:
    //a:使用SoftReference  缺点:是一定的内存不足的情况下才会回收，如果内存足，这个泄漏一直存在
    //b:使用weakReference   缺点:垃圾回收机制一过来就会回收，根据业务需求，如果要调用activity的某些方法，
    // activity就被回收了跟业务需求不符合
    //c:使用static关键字，它就不是个内部类，缺点：消耗内存太大，加载效率慢
    //d:直接在onDestroy移除handler   缺点：不好维护
    //e:啥也不干  handler做的事情不多    优点：好处理   缺点：对业务逻辑十分了解,handler生命周期特别了解，对activity生命周期特别了解
    //代码review  带新人
    private static Handler handler = new Handler(){

        private SoftReference<MainActivity> activity;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.sendEmptyMessageDelayed(handle_flag,1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeMessages(handle_flag);
    }
}
