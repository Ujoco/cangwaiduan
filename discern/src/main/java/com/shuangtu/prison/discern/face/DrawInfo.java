package com.shuangtu.prison.discern.face;

import android.graphics.Rect;

public class DrawInfo {

    private Rect rect ;
    private UserFeatureVo userFeatureVo;

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public UserFeatureVo getUserFeatureVo() {
        return userFeatureVo;
    }

    public void setUserFeatureVo(UserFeatureVo userFeatureVo) {
        this.userFeatureVo = userFeatureVo;
    }
}
