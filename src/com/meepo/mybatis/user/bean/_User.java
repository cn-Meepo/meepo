package com.meepo.mybatis.user.bean;

import java.io.Serializable;

public class _User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2712667025024833557L;

	protected Integer id;

	protected String loginNo;

	protected String password;

	protected Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo == null ? null : loginNo.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", loginNo=" + loginNo + ", password=" + password + ", status=" + status + "]";
	}
	
    
}