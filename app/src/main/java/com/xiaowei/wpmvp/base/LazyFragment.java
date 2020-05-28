package com.xiaowei.wpmvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 懒加载
 * 可见-》不可见，一瞬间停止加载， 不可见-》可见，一瞬间加载
 * popupwindow不会影响你的生命周期，dialog也不会
 */

public abstract class LazyFragment extends Fragment {

    View rootView;
    boolean isViewCreated = false;
    boolean currentVisiable = false;
    long currentTime = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(),container,false);
        }
        initView(rootView);
        isViewCreated = true;
        if (getUserVisibleHint()) {
            dispatchUserVisibleStateHint(true);
        }
        return rootView;
    }

    protected abstract int getLayoutRes();

    protected abstract void initView(View rootView);

    //判断fragment是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser && !currentVisiable) {
                dispatchUserVisibleStateHint(true);
            } else if (!isVisibleToUser && currentVisiable){
                dispatchUserVisibleStateHint(false);
            }
        }
    }

    //分发事件
    private void dispatchUserVisibleStateHint(boolean visibleState) {
        if (currentVisiable == visibleState) return;
        currentVisiable = visibleState;
        if (visibleState) {
            if (System.currentTimeMillis() - currentTime > 5000) {
                onFragmentLoad();
                currentTime = System.currentTimeMillis();
            }
        } else {
            onFragmentLoadStop();
        }
    }

    /**
     * 加载数据
     */
    private void onFragmentLoad() {
    }

    /**
     * 停止加载数据
     */
    private void onFragmentLoadStop() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!currentVisiable && getUserVisibleHint()) {
            dispatchUserVisibleStateHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (currentVisiable && getUserVisibleHint()) {
            dispatchUserVisibleStateHint(false);
        }
    }
}
