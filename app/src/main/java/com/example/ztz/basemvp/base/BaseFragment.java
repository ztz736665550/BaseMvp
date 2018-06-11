package com.example.ztz.basemvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ztz.basemvp.mvp.view.dialog.ActingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/15.
 * fragment的基类（对应mvp中的fragment）
 */

public abstract class BaseFragment<V, P extends BaseContract.BasePresenter<V>> extends Fragment {
    private static final String TAG = "BaseFragment";
    protected View parentView;
    protected FragmentActivity activity;

    protected Context mContext;
    //请求网路的进度弹窗
    public ActingDialog actingDialog;
    private Unbinder unbinder;
    //网络逻辑处理对象
    protected P mPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        Log.i(TAG, "onCreateView: ");
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        return parentView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        unbinder = ButterKnife.bind(this, view);
        attachView();
        //初始化
        mPresenter = initPresenter();
        initDatas();
        configViews();
        if(mPresenter!=null){
            mPresenter.attachView((V)this);
        }
    }
    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach: ");
        super.onDetach();
        this.activity = null;
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView: ");
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter!=null) {
            mPresenter.detachView();
        }
    }


    protected View getParentView() {
        return parentView;
    }

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }
    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.activity.getApplicationContext();
    }

    public abstract
    @LayoutRes
    int getLayoutResId();

    public abstract void attachView();

    public abstract void initDatas();

    protected abstract P initPresenter();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();
    /**
     * 显示进度弹框，BaseActivity自带
     */
    public void showDialog(){
        if(actingDialog == null){
            actingDialog = new ActingDialog();
            actingDialog.setCallBack(new ActingDialog.OnDisMissCallBack() {
                @Override
                public void disMiss() {
                    if (mPresenter == null) {
                        return;
                    }
                    //((BasePresenter)mPresenter).cancelCurrent();
                }
            });
        }
        if (!actingDialog.isVisible()) {
            actingDialog.show(getFragmentManager(),"ActingDialog");
        }
    }

    /**
     * 消失
     */
    public void disMissDialog(){
        if (actingDialog != null){
            try {
                actingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
