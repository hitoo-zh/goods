package com.jee.ssm.model;

import com.jee.ssm.model.base.BaseModel;

import java.util.Date;

public class Out extends BaseModel {

    private String id;

    private String type;

    private String goodsId;

    private Integer count;

    private String mark;

    private Date createTime;

    private String createBy;

    private String gooName;

    public String getGooName() {
        return gooName;
    }

    public void setGooName(String gooName) {
        this.gooName = gooName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}