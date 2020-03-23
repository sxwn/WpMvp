package com.xiaowei.wpmvp.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.xiaowei.wpmvp.MyApp;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求搞定！这里很明显看出 Volley 的局限，就是不支持 POST 大数据，因此不适合上传文件（下载文件倒是可以通过 DownloadManager 实现）。
 * 如果你的项目有上传文件需求，应该转战 Retrofit 或 OkHttp。
 */

public class RequestManager {
    private RequestQueue queue;
    private static volatile RequestManager instance;

    private RequestManager() {
        queue = Volley.newRequestQueue(MyApp.getInstance());
    }

    public static RequestManager getInstance() {
//        双重校验锁单例模式
        if (instance == null) {
            synchronized (RequestManager.class) {
                if (instance == null) {
                    instance = new RequestManager();
                }
            }
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return queue;
    }


    public <T> void sendGet(String url, Class<T> clazz, final MyListener<T> listener) {
        MyRequest<T> request = new MyRequest<>(url, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }

        });
        addToRequestQueue(request);
    }

    public <T> void sendPost(String url, Class<T> clazz, final HashMap<String, String> map, final MyListener<T> listener) {
        MyRequest<T> request = new MyRequest<T>(Request.Method.POST, url, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        addToRequestQueue(request);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
