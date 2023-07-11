package com.taihu.secendcar.service;

import com.taihu.secendcar.entity.Brand;

import java.util.List;

/**
 * IBrandService
 *
 * @author hcj
 * @date 2023-06-27
 */
public interface IBrandService {
    // 根据品牌id查询一条品牌数据
    Brand findBrandById(String id);

    // 查询所有的品牌集合
    List<Brand> findBrandAll();
}
