package com.shuangtu.prison.discern.face.http.common.Entity;

/**

 **/

public class ResponseEntity<T> {
	private int success;
	private String  code;
    private String message;
    private T data;
	
    public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}    
}
