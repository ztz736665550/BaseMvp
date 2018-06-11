package com.example.ztz.basemvp.base;

/**
 * Created by Administrator on 2017/8/15.
 * 对应mvp中的contract契约接口
 */

public interface BaseContract {
    interface BasePresenter<T> {
        void attachView(T view);
        void detachView();
    }

    interface BaseView {
        void showError(Throwable e);
        void complete();
    }
}
