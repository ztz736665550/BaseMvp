package com.example.ztz.basemvp.mvp.presenter;

import com.example.ztz.basemvp.base.BasePresenter;
import com.example.ztz.basemvp.bean.TestBean;
import com.example.ztz.basemvp.mvp.contract.TestContract;
import com.example.ztz.basemvp.mvp.model.TestModel;

/**
 * Created by ztz on 2018/6/5.
 */

public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    private final TestModel mTestModel;

    public TestPresenter(){
        mTestModel = new TestModel();
    }


    public void getData() {
        mTestModel.getData(this);
    }

    @Override
    public void suc(TestBean str) {
        mView.completed(str);
    }

    @Override
    public void err() {
        mView.showError(new Throwable("err"));
    }

}
