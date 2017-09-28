package com.meepo.mybatis.employee.bean;

import javax.validation.constraints.Pattern;

import com.meepo.mybatis.department.bean._Department;



public class _Employee {
	protected Integer id;

	@Pattern(regexp = "(^[a-z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})", message = "用户名必须是6-16位数字或字母的组合或2-5位中文")
	protected String name;

	protected String gender;

	// @Email
	// 或者
	@Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message = "邮箱格式不正确")
	protected String email;

	protected Integer dId;

	// 希望查询员工的同时部门信息也是查询好的
	protected _Department department;

	protected Integer status;

	protected Integer type;

	public _Department getDepartment() {
		return department;
	}

	public void setDepartment(_Department department) {
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}