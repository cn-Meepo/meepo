package com.meepo.mybatis.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.neza.encrypt.BASE64;

public class Utils {
	public static JSONObject getJSONObject(HttpServletRequest request) {
		String content = request.getParameter("content");
		if (content == null)
			return null;
		try {
			String params = URLDecoder.decode(BASE64.dencrypt(content), "UTF-8");
			return JSONObject.parseObject(params);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/** 
	* 获取对象属性，返回一个字符串数组     
	*  
	* @param  o 对象 
	* @return String[] 字符串数组 
	*/ 
	public static String[] getFiledName(Object o) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				fieldNames[i] = fields[i].getName();
			}
			return fieldNames;
		} catch (SecurityException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return null;
	}
	/** 
	* 使用反射根据属性名称获取属性值  
	*  
	* @param  fieldName 属性名称 
	* @param  o 操作对象 
	* @return Object 属性值 
	*/  
	  
	public Object getFieldValueByName(String fieldName, Object object)   
	{      
	   try   
	   {      
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();      
	       String getter = "get" + firstLetter + fieldName.substring(1);      
	       Method method = object.getClass().getMethod(getter, new Class[] {});      
	       Object value = method.invoke(object, new Object[] {});      
	       return value;      
	   } catch (Exception e)   
	   {      
	       System.out.println("属性不存在");      
	       return null;      
	   }      
	} 
}
