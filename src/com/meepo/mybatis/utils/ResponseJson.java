package com.meepo.mybatis.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 

public class ResponseJson {
	private static final long serialVersionUID = 1L;
	private String err = "-1";
	private String msg = "";	
	public JSONObject json = new JSONObject();

	public ResponseJson() {
		super();
		 
	}
	
	public ResponseJson(JSONObject json) {
		super();
		this.json = json;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		json.put("err", err);
		this.err = err;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		json.put("msg", msg);
		this.msg = msg;
	}

	public ResponseJson addErr(String err) {
		this.err=err;
		json.put("err", err);

		return this;
	}

	public ResponseJson addResult(JSONObject result) {
		json.put("result", result);

		return this;
	}
	
	public ResponseJson add(String key,JSONObject result) {
		json.put(key, result);

		return this;
	}
	
	public String get(String key) {
		if(json.containsKey(key)){
			return json.getString(key);
		}
		return "";
	}
	
	public ResponseJson addResult(String  resultJsonStr) {
		json.put("result", resultJsonStr);
		return this;
	}
	
	
	public ResponseJson add(String key,String  resultJsonStr) {
		json.put(key, resultJsonStr);

		return this;
	}
	
	
	public ResponseJson addResult(JSONArray  list) {
		json.put("result", list);
		return this;
	}
	
	
	public ResponseJson add(String key,JSONArray  list) {
		json.put(key, list);
		return this;
	}
	
	public ResponseJson add(JSONObject jsonObject) {
		if(jsonObject!=null)
		  json.putAll(jsonObject);
		return this;
	}
	
	public ResponseJson addMsg(String msg) {
		json.put("msg", msg);
		return this;
	}
	public String toJsonStr() {		 
	 
		return json.toString();
	}
	
	public JSONObject toJson() {		 
		return json;
	}

	
	public static void main(String[] args) {
		
		ResponseJson rj=new ResponseJson();
		 JSONObject json = new JSONObject();
		 json.put("name","tom");
		 rj.addErr("0");
		 rj.addMsg("OK");
		 rj.addResult("{id:2,name:666}");
		 rj.add("json",json);
		System.out.println(rj.toJsonStr());
	}
	/*{"err":0,"msg":"","result":{"productName":"油","quantity":200}}
	{"err":0,"msg":"","list":[{"orderId":"sdksdsdjsdid254as45","no":"O201606080001","saleTime":"2016-06-08 12:52:20",items:[{"productName":"油","price":"9.9","quantity":200}},{"orderId":"sdksdsdjsdid254as45","no":"O201606080001","saleTime":"2016-06-08 12:52:20",items:[{"productName":"油","price":"9.9","quantity":200}}]}*/
}
