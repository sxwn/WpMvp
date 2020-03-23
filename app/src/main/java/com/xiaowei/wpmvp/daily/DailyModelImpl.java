package com.xiaowei.wpmvp.daily;

import com.xiaowei.wpmvp.base.MvpListener;
import com.xiaowei.wpmvp.network.MyListener;
import com.xiaowei.wpmvp.network.RequestManager;

import java.util.List;

public class DailyModelImpl implements DailyContract.DailyModel {
    @Override
    public void loadDaily(String url, final MvpListener<List<DailyBean.StoriesBean>> listener) {
        RequestManager.getInstance().sendGet(url, DailyBean.class, new MyListener<DailyBean>() {
            @Override
            public void onSuccess(DailyBean result) {
                listener.onSuccess(result.getStories());
            }

            @Override
            public void onError(String errorMsg) {
                listener.onError(errorMsg);
            }
        });
    }
}
