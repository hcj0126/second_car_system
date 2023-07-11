package com.taihu.secendcar.entity;

import com.taihu.secendcar.constant.BaseConstant;

import java.util.Date;

/**
 * Car
 *  车辆实体类，对应数据库car表
 * @author hcj
 * @date 2023-06-26
 */
public class Car {
    private String id; // 车辆编号
    private String modelId; // 车型编号
    private Integer mileage; // 里程数
    private Double price; // 价格
    private Date publishDate; // 发布日期
    private String displacement; // 排量
    private Date passDate; // 上牌日期
    private Integer clutchType; // 离合器类型 0:手动档 1:自动档 2:手自一体 3:油电混动 4:纯电动
    private Integer flag; // 是否上架 0:下架  1:上架

    // 非数据库字段
    private String clutchTypeStr;

    public Car() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public Integer getClutchType() {
        return clutchType;
    }

    public void setClutchType(Integer clutchType) {
        this.clutchType = clutchType;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    // 离合器类型 0:手动档 1:自动档 2:手自一体 3:油电混动 4:纯电动
    public String getClutchTypeStr() {
        if(clutchType== BaseConstant.CAR_TYPE_ZERO){
            clutchTypeStr = "手动档";
        }else if(clutchType==BaseConstant.CAR_TYPE_ONE){
            clutchTypeStr = "自动档";
        }else if(clutchType==BaseConstant.CAR_TYPE_TWO){
            clutchTypeStr = "手自一体";
        }else if(clutchType==BaseConstant.CAR_TYPE_THREE){
            clutchTypeStr = "油电混动";
        }else if(clutchType==BaseConstant.CAR_TYPE_FOUR){
            clutchTypeStr = "纯电动";
        }
        return clutchTypeStr;
    }

    public void setClutchTypeStr(String clutchTypeStr) {
        this.clutchTypeStr = clutchTypeStr;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", modelId='" + modelId + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", publishDate=" + publishDate +
                ", displacement='" + displacement + '\'' +
                ", passDate=" + passDate +
                ", clutchType=" + clutchType +
                ", flag=" + flag +
                '}';
    }
}
