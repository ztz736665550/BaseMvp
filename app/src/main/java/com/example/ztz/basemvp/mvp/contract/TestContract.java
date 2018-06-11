package com.example.ztz.basemvp.mvp.contract;

import com.example.ztz.basemvp.base.BaseContract;
import com.example.ztz.basemvp.bean.TestBean;

/**
 * Created by ztz on 2018/6/5.
 */

public interface TestContract {

    interface View extends BaseContract.BaseView{

        void completed(TestBean str);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void suc(TestBean str);
        void err();
    }
}
