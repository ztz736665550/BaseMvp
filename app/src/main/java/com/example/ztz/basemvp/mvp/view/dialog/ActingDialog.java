package com.example.ztz.basemvp.mvp.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.ztz.basemvp.R;
import com.example.ztz.basemvp.base.BaseDialogV4;
import com.example.ztz.basemvp.utils.ScreenUtils;


/**
 * Created by Administrator on 2017/8/16.
 * 用于网络请求展示的dialog
 */

public class ActingDialog extends BaseDialogV4 {

    @Override
    public int getLayoutId() {
        return R.layout.dialog_acting;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void config(Dialog dialog) {
        //设置弹窗点击外面不消失
        //dialog.setCancelable(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (callBack!=null) {
                        callBack.disMiss();
                    }
                    return false;
                }
                return false;
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    @Override
    protected void initOnStart() {
        if (getDialog() == null) {
            return;
        }
        getDialog().getWindow().setGravity(
                Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        //设置该dialog为屏幕的7/15
        lp.width = (int)(ScreenUtils.getScreenWidth()*0.28f);
        lp.height = lp.width;
        //设置周围完全透明
        lp.dimAmount = 0;
        getDialog().getWindow().setAttributes(lp);
//        getDialog().setCancelable(false);
        //防止窗口抖动
//        getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //隐藏navigationBar
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_IMMERSIVE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        getDialog().getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }
    public interface OnDisMissCallBack{
        void disMiss();
    }
    private OnDisMissCallBack callBack;

    public void setCallBack(OnDisMissCallBack callBack) {
        this.callBack = callBack;
    }
}
