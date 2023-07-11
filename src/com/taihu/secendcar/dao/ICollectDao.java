package com.taihu.secendcar.dao;

import com.taihu.secendcar.entity.Car;
import com.taihu.secendcar.entity.Collect;

import java.util.LinkedList;
import java.util.List;

/**
 * ICollectDao
 *
 * @author hcj
 * @date 2023-06-29
 */
public interface ICollectDao {
    // 根据用户id查询此用户下所有的收藏收藏
    LinkedList<Collect> queryCollectCarByUserId(String userId);

    // 添加收藏车辆信息
    void insertCollect(Collect c);

}
