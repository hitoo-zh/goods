package com.jee.ssm.model;

import com.jee.ssm.model.base.BaseModel;

import java.util.Date;


public class BannerCat extends BaseModel {

    private String id;

    private String name;

    private String sort;

    private String mark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}