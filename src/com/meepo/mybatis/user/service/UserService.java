package com.meepo.mybatis.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meepo.mybatis.user.User;
import com.meepo.mybatis.user.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User selectByLoginNo(String loginNo) {
		return userMapper.selectByLoginNo(loginNo);
	}

	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
