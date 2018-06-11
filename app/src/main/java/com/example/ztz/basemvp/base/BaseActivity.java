package com.example.ztz.basemvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ztz.basemvp.mvp.view.dialog.ActingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/15.
 * Activity基类，可以设置统一的dialog、权限设置等(对应mvp中的activity)
 */

public abstract class BaseActivity<V, P extends BaseContract.BasePresenter<V>>  extends AppCompatActivity {
    protected Context mContext;
    //ButterKnife绑定对象
    private Unbinder unbinder;
    //请求网路的进度弹窗
    public ActingDialog actingDialog;
    //网络逻辑处理对象
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //设置软键盘、沉浸式
        initWindow();
        //设置布局文件
        setContentView(getLayoutId());
        //绑定
        unbinder = ButterKnife.bind(this);
        //初始化
        mPresenter = initPresenter();
        initDatas();
        configViews();
        if(mPresenter!=null){
            mPresenter.attachView((V)this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        unbinder.unbind();
        //
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
    }
    protected abstract P initPresenter();
    public abstract int getLayoutId();
    public abstract void initWindow();
    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();

    /**
     * 初始化控件数据
     */
    public abstract void initDatas();

    /**
     * 显示进度弹框，BaseActivity自带
     */
    public void showDialog(){
        if(actingDialog == null){
            actingDialog = new ActingDialog();
        }
        if (!actingDialog.isVisible()) {
            actingDialog.show(getSupportFragmentManager(),"ActingDialog");
        }
        //设置弹窗点击外面不消失
        actingDialog.setCancelable(false);
    }

    /**
     * 消失
     */
    public void disMissDialog(){
        if (actingDialog != null){
            actingDialog.dismiss();
        }
    }
}
