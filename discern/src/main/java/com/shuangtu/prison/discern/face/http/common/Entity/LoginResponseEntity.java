package com.shuangtu.prison.discern.face.http.common.Entity;



/**
 * {
    "success": 1,
    "code": "0000",
    "message": "操作成功",
    "data": {
        "id": "1",
        "account": "weng",
        "trueName": "wengzhonghui",
        "loginType": "1",
        "expireTimeLong": 7200,
        "freshTime": 1583069468182,
        "token": "ef97e0b89ed34126955663a3b7457e00",
        "pris": null
    }
} 
 * @author 86139
 *
 */
public class LoginResponseEntity {
	
	//用户ID
	private String id;
	//账号
	private String account;
	//用户名称
	private String trueName;
	private String loginType;
	private long expireTimeLong;
	private long freshTime;
	//登录获取的token
	private String token;
	private String pris;
	
	
	public String getPris() {
		return pris;
	}
	public void setPris(String pris) {
		this.pris = pris;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public long getExpireTimeLong() {
		return expireTimeLong;
	}
	public void setExpireTimeLong(long expireTimeLong) {
		this.expireTimeLong = expireTimeLong;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public long getFreshTime() {
		return freshTime;
	}
	public void setFreshTime(long freshTime) {
		this.freshTime = freshTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
