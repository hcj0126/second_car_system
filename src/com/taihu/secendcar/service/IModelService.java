package com.taihu.secendcar.service;

import com.taihu.secendcar.entity.Model;

import java.util.List;

/**
 * IModelService
 *
 * @author hcj
 * @date 2023-06-27
 */
public interface IModelService {
    // 根据车型id查询一条车型数据
    Model findModelById(String id);

    // 根据brandId到model表中查询Model对象集合
    List<Model>  findModelByBrandId(String brandId);
}
