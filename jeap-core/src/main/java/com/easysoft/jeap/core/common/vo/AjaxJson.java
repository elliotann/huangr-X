package com.easysoft.jeap.core.common.vo;

/**
 * Created by huangxa on 2014/7/16.
 */
public class AjaxJson {
    private boolean success = true;
    private String msg="操作成功!";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
