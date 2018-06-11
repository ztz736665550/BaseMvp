package com.example.ztz.basemvp.base;

/**
 * Created by Administrator on 2017/8/15.
 * 基于Rx的Presenter封装,控制订阅的生命周期(presenter的基类，对应mvp中的presenter)
 * 防止被观察者对订阅对象的引用
 */

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    private static final String TAG = "BasePresenter";
    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
