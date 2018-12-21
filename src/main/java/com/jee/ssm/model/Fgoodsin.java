package com.jee.ssm.model;

import com.jee.ssm.model.base.BaseModel;


public class Fgoodsin extends BaseModel {

    private String id;
    private String goodsId;
    private String storeId;
    private String storeName;
    private String goodsNum;
    private String createTime;
    private String createManagerId;
    private String mark;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateManagerId() {
        return createManagerId;
    }

    public void setCreateManagerId(String createManagerId) {
        this.createManagerId = createManagerId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}