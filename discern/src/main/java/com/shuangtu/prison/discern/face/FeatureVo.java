package com.shuangtu.prison.discern.face;


public class FeatureVo {

    /**
     * 用户id
     */
    String uid;

    int type;

    /**
     * 人脸特诊
     */

    float[] feature;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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
