package com.shuangtu.prison.discern.face;


public class UserFeatureVo {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名称
     */
    private String trueName;

    /**
     * 电话
     */
    private String phone;

    /**
     * path
     */
    //private String path;

    private String token;

    private String avatar;

    @Override
    public String toString() {
        return "UserFeatureVo [id=" + id + ", trueName=" + trueName + ", phone=" + phone + "]";
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //public String getPath() {		return path;	}

    //public void setPath(String path) {		this.path = path;	}

}
