package com.shuangtu.prison.discern.face.http.common.Entity;


public class FeatureVo {
	
	/**
	 * 用户id
	 */	
	String uid;
	
	
	/**
	 * 人脸特诊
	 */
	
	float[] feature;


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public float[] getFeature() {
		return feature;
	}


	public void setFeature(float[] feature) {
		this.feature = feature;
	}
		
}
