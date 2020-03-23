package com.xiaowei.wpmvp.daily;

import com.xiaowei.wpmvp.base.BaseModel;
import com.xiaowei.wpmvp.base.BasePresenter;
import com.xiaowei.wpmvp.base.BaseView;
import com.xiaowei.wpmvp.base.MvpListener;

import java.util.List;

/**
 * 契约接口 DailyContract
 * 接口里同时承载了 Daily 这个模块的 M，V 和 P（现在明白为何一开始要把 BasePresenter 弄成抽象类了吧），并且定义了方法规则
 */

public interface DailyContract {
    interface DailyModel extends BaseModel {
        void loadDaily(String url, MvpListener<List<DailyBean.StoriesBean>> listener);
    }

    interface DailyView extends BaseView {
        void setData(List<DailyBean.StoriesBean> beanList);
    }

    abstract class DailyPresenter extends BasePresenter<DailyModel, DailyView> {
        protected abstract void loadData(String url);
    }

}
