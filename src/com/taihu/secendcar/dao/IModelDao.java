package com.taihu.secendcar.dao;

import com.taihu.secendcar.entity.Model;

import java.util.List;

/**
 * IModerDao
 *
 * @author hcj
 * @date 2023-06-27
 */
public interface IModelDao {
    // 根据车型id查询一条车型数据
    Model queryModelById(String id);

    // 根据brandId到model表中查询Model对象集合
    List<Model> queryModelByBrandId(String brandId);
}
