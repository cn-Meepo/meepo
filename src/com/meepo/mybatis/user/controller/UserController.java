package com.meepo.mybatis.user.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.meepo.mybatis.user.User;
import com.meepo.mybatis.user.service.UserService;
import com.meepo.mybatis.utils.ConstantUtil;
import com.meepo.mybatis.utils.JedisPoolUtil;
import com.meepo.mybatis.utils.LoginRightInfo;
import com.meepo.mybatis.utils.Msg;
import com.meepo.mybatis.utils.ResponseJson;
import com.meepo.mybatis.utils.SerializeUtil;
import com.meepo.mybatis.utils.Utils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/login")
	public Msg login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = Utils.getJSONObject(request);
		if (request.getSession().getAttribute("code").toString().equalsIgnoreCase(json.getString("code"))) {
			String loginNo = json.getString("loginNo");
			String password = json.getString("password");
			User user = userService.selectByLoginNo(loginNo);
			if (user != null && !user.getId().equals("")) {
				if (user.getPassword().equals(password)) {
					LoginRightInfo URI = new LoginRightInfo();
					URI.setUser(user);
					session.setAttribute("URI", URI);
					request.setAttribute("URI", URI);
					JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
					Jedis jedis = null;
					try {
						jedis = jedisPool.getResource();
						jedis.set(user.getId().toString().getBytes(), SerializeUtil.serialize(user));
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						JedisPoolUtil.release(jedisPool, jedis);
					}
					return Msg.success().add("msg", "登录成功");
				} else {
					return Msg.failed().add("msg", "密码不正确");
				}
			} else {
				return Msg.failed().add("msg", "没有此用户！");
			}
		} else {
			return Msg.failed().add("msg", "验证码错误！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/logout")
	public Msg logout(HttpSession session) throws Exception {
		// 清除Session
		session.invalidate();
		return Msg.success().add("msg", "注销成功");
	}

	@RequestMapping(value = "/viewSelf")
	public String getEmps(HttpServletRequest request, HttpServletResponse response) {
		LoginRightInfo URI = (LoginRightInfo) request.getSession().getAttribute("URI");
		User user = userService.selectByPrimaryKey(URI.getUser().getId());
		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			User tempUser = (User) SerializeUtil.getObject(user.getId().toString()); 
			System.out.println(tempUser.getLoginNo());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisPoolUtil.release(jedisPool, jedis);
		}

		request.setAttribute("user", user);
		return "user/viewUser";
	}
}
