package com.jee.ssm.model;

import com.jee.ssm.model.base.BaseModel;

import java.util.Date;


public class Fgoods extends BaseModel {

    private String id;
    private String name;
    private String img;
    private Float jinPrice;
    private Float salePrice;
    private String huoYuan;
    private String classId;
    private String createBy;

    private Date createTime;
    private String className;  /*分类名称*/


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Float getJinPrice() {
        return jinPrice;
    }

    public void setJinPrice(Float jinPrice) {
        this.jinPrice = jinPrice;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public String getHuoYuan() {
        return huoYuan;
    }

    public void setHuoYuan(String huoYuan) {
        this.huoYuan = huoYuan;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}