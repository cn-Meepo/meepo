package com.meepo.mybatis.utils;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meepo.mybatis.user.bean._User;
import com.neza.base.*;


public class LoginRightInfo extends BaseRightInfo {
	private static final long serialVersionUID = 1L;

	private _User user;
	// private String
	// context="sa01,sa02,sa03,sa04,sa05,sa06,sa07,sa08,sa09,sa10,sa11,sa12,sb01,sb02,sc01,sc02,sc03,sc04,sc05,sc06,sc07,sc08,sc09,sc10,sc11,sc12,sc13,sc14,sd01,sd02,sd03,sd04,sd05,sd06,se01,se02,se03,se04,sf01,sf02,sf03,sf04,sf05,sf06,sh01,sh02,sh03,sh04,sh05,sh06,sh10,sh11,sg01,sg02,sr01,sr02,sr03";
	// private String context="";
	// private JSONObject menu = new JSONObject();

	public _User getUser() {
		return user;
	}

	public void setUser(_User user) {
		this.user = user;
	}

	public String getIp(HttpServletRequest request) {
		if (request != null)
			return request.getRemoteAddr();
		return "";
	}

	private String trans2(int temp) {
		if (temp >= 0 && temp < 10)
			return (new StringBuilder("0")).append(temp).toString();
		if (temp >= 10 && temp < 99)
			return (new StringBuilder()).append(temp).toString();
		else
			return "00";
	}

	private String[] trans(String str1, String str2) {
		String codes[] = new String[0];
		String temp = str1.substring(0, 2);
		int a = Constant.toInt(str1.substring(2));
		int b = Constant.toInt(str2.substring(2));
		if (b > a)
			codes = new String[(b - a) + 1];
		for (int i = 0; i < (b - a) + 1; i++)
			codes[i] = (new StringBuilder(String.valueOf(temp))).append(trans2(a + i)).toString();
		return codes;
	}

	private boolean check(String code) {
		String codes[] = new String[0];
		String codess[] = new String[0];
		boolean b = false;
		if (code.indexOf(",") > 0) {
			codes = code.split(",");
			for (int i = 0; i < codes.length; i++)
				if (codes[i].indexOf("-") > 0) {
					String str1 = codes[i].split("-")[0];
					String str2 = codes[i].split("-")[1];
					codess = trans(str1, str2);
					for (int j = 0; j < codess.length; j++)
						b = b || this.hasRight(codess[j]);
				} else {
					b = b || this.hasRight(codes[i]);
				}

		} else if (code.indexOf("-") > 0) {
			String str1 = code.split("-")[0];
			String str2 = code.split("-")[1];
			codess = trans(str1, str2);
			for (int j = 0; j < codess.length; j++)
				b = b || this.hasRight(codess[j]);
		} else {
			b = this.hasRight(code);
		}
		return b;
	}

}
