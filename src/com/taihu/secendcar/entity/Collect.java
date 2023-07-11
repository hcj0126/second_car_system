package com.taihu.secendcar.entity;

import java.util.Date;

/**
 * Collect
 *  收藏实体类，对应数据库collect表
 * @author hcj
 * @date 2023-06-29
 */
public class Collect {
    private String id; // 编号
    private String carId; // 车辆编号
    private String userId; // 用户编号
    private Date createDate; // 创建时间
    private Date updateDate; // 更新时间

    public Collect() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id='" + id + '\'' +
                ", carId='" + carId + '\'' +
                ", userId='" + userId + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
