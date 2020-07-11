package com.shuangtu.prison.discern.face.http.common.Entity;

public class LoginEntity {
	//账号
	private String userName;
	//密码
	private String password;
	//登陆类型，1用户登陆，2终端登陆
	private String loginType;
	
	public LoginEntity(String userName,String password,String loginType)	
	{
		this.userName = userName;
		this.password = password;
		this.loginType = loginType;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	

}
