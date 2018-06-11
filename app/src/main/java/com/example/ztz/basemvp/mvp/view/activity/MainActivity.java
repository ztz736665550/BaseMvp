package com.example.ztz.basemvp.mvp.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ztz.basemvp.R;
import com.example.ztz.basemvp.base.BaseActivity;
import com.example.ztz.basemvp.bean.TestBean;
import com.example.ztz.basemvp.mvp.contract.TestContract;
import com.example.ztz.basemvp.mvp.presenter.TestPresenter;
import com.example.ztz.basemvp.utils.MultipleStatusView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<TestContract.View, TestContract.Presenter> implements TestContract.View {


    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    private TestPresenter mTestPresenter;

    @Override
    protected TestContract.Presenter initPresenter() {
        mTestPresenter = new TestPresenter();
        mTestPresenter.attachView(this);
        return mTestPresenter;
    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    public void initWindow() {

    }

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        mTestPresenter.getData();
        multipleStatusView.showLoading();
    }

    @Override
    public void showError(Throwable e) {
        multipleStatusView.showError();
    }

    @Override
    public void complete() {

    }

    @Override
    public void completed(TestBean str) {
        multipleStatusView.showContent();
        text.setText(str.getIssueList().get(0).getItemList().get(2).getData().getTitle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTestPresenter.detachView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
