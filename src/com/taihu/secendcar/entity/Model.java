package com.taihu.secendcar.entity;

/**
 * Model
 *  车型实体类，对应数据库model表
 * @author hcj
 * @date 2023-06-26
 */
public class Model {
    private String id; // 车型编号
    private String modelName; // 车型名称
    private String brandId; // 品牌编号

    public Model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", modelName='" + modelName + '\'' +
                ", brandId='" + brandId + '\'' +
                '}';
    }
}
