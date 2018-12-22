package com.jee.ssm.model;

import com.jee.ssm.model.base.BaseModel;

public class Grade extends BaseModel {

    private String id;

    private String gradeName;

    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}