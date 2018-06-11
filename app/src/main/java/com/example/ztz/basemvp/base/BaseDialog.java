package com.example.ztz.basemvp.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/9/21.
 */

public abstract class BaseDialog extends DialogFragment {
    @Override
    public void onStart() {
        super.onStart();
        initOnStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //设置dialog的样式
        Dialog dialog = new Dialog(getActivity(),setStyle()) {
            @Override
            public void onBackPressed() {
                super.onBackPressed();
            }

        };
        config(dialog);
        return dialog;
    }

    /**
     * 设置弹窗的样式
     * @param
     */
    protected abstract int setStyle();

    /**
     * 设置布局
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据，在初始化控件之前调用
     */
    protected abstract void initData();

    /**
     * 设置dialog全屏显示
     * @param dialog
     */
    protected abstract void config(Dialog dialog);

    /**
     * 窗口的基本设置，包括宽高、动画、渐变、是否有navigationBar等
     */
    protected abstract void initOnStart();
}
