package com.es.jeap.core.common.json;
/**
 * ajax操作结果返回对象
 * @author huangxa
 *
 */
public class AjaxJsonResult {
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
