package com.meepo.mybatis.user;

import com.alibaba.fastjson.JSONObject;
import com.meepo.mybatis.user.bean._User;

public class User extends _User {



	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("loginNo", loginNo);
		json.put("status", status);
		return json;
	}

	public String getStatusInfo() {
		String temp = "";
		if (this.status == 0) {
			temp = "禁用";
		} else if (this.status == 1) {
			temp = "正常";
		} else {
			temp = "异常";
		}
		return temp;
	}
}
