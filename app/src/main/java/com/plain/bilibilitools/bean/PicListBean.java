package com.plain.bilibilitools.bean;

/**
 * Author : Plain
 * ClassName : com.plain.bilibilitools.bean
 * Description :
 * CreateDate : 2019-08-02 15:40
 * Version : V1.0
 */
public class PicListBean {

    private String picUrl;
    private String picMsg;
    private String jumpType;

    public PicListBean(String picUrl, String picMsg, String jumpType) {
        this.picUrl = picUrl;
        this.picMsg = picMsg;
        this.jumpType = jumpType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicMsg() {
        return picMsg;
    }

    public void setPicMsg(String picMsg) {
        this.picMsg = picMsg;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    @Override
    public String toString() {
        return "PicListBean{" +
                "picUrl='" + picUrl + '\'' +
                ", picMsg='" + picMsg + '\'' +
                ", jumpType='" + jumpType + '\'' +
                '}';
    }
}
