package com.meepo.mybatis.employee;

import com.alibaba.fastjson.JSONObject;
import com.meepo.mybatis.employee.bean._Employee;

public class Employee extends _Employee implements java.io.Serializable{

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		json.put("gender", gender);
		json.put("email", email);
		json.put("type", type);
		json.put("status", status);
		return json;
	}
}
