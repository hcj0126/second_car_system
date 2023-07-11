package com.taihu.secendcar.entity;

/**
 * Brand
 *  品牌实体类，对应数据库brand表
 * @author hcj
 * @date 2023-06-26
 */
public class Brand {
    private String id; // 品牌编号
    private String brandName; // 品牌名称

    public Brand() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id='" + id + '\'' +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
