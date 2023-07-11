package com.taihu.secendcar.service;

import com.taihu.secendcar.entity.Collect;

import java.util.LinkedList;
import java.util.List;

/**
 * ICollectService
 *
 * @author hcj
 * @date 2023-06-29
 */
public interface ICollectService {
    // 根据用户id查询此用户下所有的收藏收藏
    LinkedList<Collect> findCollectCarByUserId(String userId);

    // 添加收藏车辆信息
    void addCollect(Collect c);
}
