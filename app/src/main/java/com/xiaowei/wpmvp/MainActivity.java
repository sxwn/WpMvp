package com.xiaowei.wpmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.xiaowei.wpmvp.api.Api;
import com.xiaowei.wpmvp.base.BaseMvpActivity;
import com.xiaowei.wpmvp.daily.DailyAdapter;
import com.xiaowei.wpmvp.daily.DailyBean;
import com.xiaowei.wpmvp.daily.DailyContract;
import com.xiaowei.wpmvp.daily.DailyModelImpl;
import com.xiaowei.wpmvp.daily.DailyPresenterImpl;
import com.xiaowei.wpmvp.utils.DateUtil;

import java.util.Date;
import java.util.List;

public class MainActivity extends BaseMvpActivity<DailyPresenterImpl, DailyModelImpl> implements DailyContract.DailyView {

    private DailyAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        adapter = new DailyAdapter(this);
        RecyclerView rcv = (RecyclerView) findViewById(R.id.ac_main_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);
        rcv.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        mPresenter.loadData(Api.DAILY_HISTORY
                + DateUtil.format(new Date(), "yyyyMMdd"));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setData(List<DailyBean.StoriesBean> beanList) {
        adapter.setBeanList(beanList);
    }
}
