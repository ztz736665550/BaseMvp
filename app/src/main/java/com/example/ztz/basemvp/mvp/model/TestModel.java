package com.example.ztz.basemvp.mvp.model;

import com.example.ztz.basemvp.bean.TestBean;
import com.example.ztz.basemvp.mvp.contract.TestContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by ztz on 2018/6/5.
 */

public class TestModel {

    public void getData(final TestContract.Presenter testPresenter) {
        OkGo.<String>post("http://baobab.kaiyanapp.com/api/v2/feed?")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        TestBean testBean = new Gson().fromJson(body, TestBean.class);
                        testPresenter.suc(testBean);
                    }
                });
    }
}
