package com.taihu.secendcar.dao;

import com.taihu.secendcar.entity.Brand;

import java.util.List;

/**
 * IBrandDao
 *
 * @author hcj
 * @date 2023-06-27
 */
public interface IBrandDao {
    // 根据品牌id查询一条品牌数据
    Brand queryBrandById(String id);

    // 查询所有的品牌集合
    List<Brand> queryBrandAll();


}
