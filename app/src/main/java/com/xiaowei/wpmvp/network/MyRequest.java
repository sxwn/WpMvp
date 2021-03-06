package com.xiaowei.wpmvp.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class MyRequest<T> extends Request<T> {

    private Gson mGSon;
    private Class<T> mClass;
    private Response.Listener<T> mListener;


    public MyRequest(String url, Class<T> clazz,
                     Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Request.Method.GET, url, clazz, listener, errorListener);
    }

    public MyRequest(int method, String url, Class<T> clazz,
                     Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mGSon = new Gson();
        mClass = clazz;
        mListener = listener;
    }

    /**
     * 解析请求到的响应（也就是返回数据）
     * @param response
     * @return
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGSon.fromJson(json, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 将响应传递给回调接口 mListener
     * @param response
     */
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}
