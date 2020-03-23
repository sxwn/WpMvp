package com.xiaowei.wpmvp.daily;

import com.xiaowei.wpmvp.base.MvpListener;

import java.util.List;

public class DailyPresenterImpl extends DailyContract.DailyPresenter {
    @Override
    public void loadData(String url) {
        final DailyContract.DailyView mView = getView();
        if (mView == null) {
            return;
        }

        mView.showLoading();
        mModel.loadDaily(url, new MvpListener<List<DailyBean.StoriesBean>>() {
            @Override
            public void onSuccess(List<DailyBean.StoriesBean> result) {
                mView.hideLoading();
                mView.setData(result);
            }

            @Override
            public void onError(String errorMsg) {
                mView.hideLoading();
                mView.showError();
            }
        });
    }
}
