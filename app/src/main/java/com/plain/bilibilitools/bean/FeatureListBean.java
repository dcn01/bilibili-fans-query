package com.plain.bilibilitools.bean;

/**
 * Create by Plain on 2019/4/10 3:15 PM
 * Description: 功能列表bean
 */
public class FeatureListBean {

    private int iconId;
    private String name;
    private String description;

    public FeatureListBean(int iconId, String name, String description) {
        this.iconId = iconId;
        this.name = name;
        this.description = description;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
