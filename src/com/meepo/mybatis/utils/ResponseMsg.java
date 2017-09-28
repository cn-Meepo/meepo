package com.meepo.mybatis.utils;

import com.alibaba.fastjson.JSONObject;

public class ResponseMsg {
	private static final long serialVersionUID = 1L;
	private String err = "";
	private String msg = "";
	private JSONObject json = new JSONObject();

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResponseMsg addErr(String err) {
		json.put("err", err);
		this.err=err;
		return this;
	}

	public ResponseMsg addMsg(String msg) {
		json.put("msg", msg);
		this.msg=msg;
		return this;
	}
	public String toJsonStr() {		 
	 
		return json.toString();
	}
	
	public JSONObject toJson() {		 
		
		return json;
	}

}
