package com.meepo.mybatis.utils;

import java.util.HashMap;
import java.util.Map;

/*
 * 通用的返回类
 * 
 */
public class Msg {

	// 状态码 100-成功； 200-失败
	private int code;
	// 提示信息
	private String msg;

	private Map<String, Object> extend = new HashMap<String, Object>();

	public static Msg success() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("success");
		return result;
	}

	public static Msg failed() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("failed");
		return result;
	}

	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

}
