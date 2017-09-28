package com.meepo.mybatis.department;

import com.alibaba.fastjson.JSONObject;
import com.meepo.mybatis.department.bean._Department;

public class Department extends _Department implements java.io.Serializable {

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		json.put("status", status);
		return json;
	}

	public String toString() {
		String temp = "";
		temp = "[id=" + id + ", name=" + name + ", status=" + status + "]";
		return temp;
	}
}
