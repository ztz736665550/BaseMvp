package com.example.ztz.basemvp.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/15.
 * 序列化对象，模型的基类，对应mvp中的model
 * 基类模型抽出共同的属性，比如与后台接口约定好的出错标记、出错信息等
 */

public class Base implements Serializable {
    /*后台接口设置的是布尔值的出错判断*/
    protected boolean State;
    /*出错信息*/
    protected String Message;

    protected boolean isState() {
        return State;
    }
    protected void setState(boolean State) {
        this.State = State;
    }
    protected String getMessage() {
        return Message;
    }
    protected void setMessage(String Message) {
        this.Message = Message;
    }
}
