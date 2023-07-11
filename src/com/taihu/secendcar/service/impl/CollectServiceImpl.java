package com.taihu.secendcar.service.impl;

import com.taihu.secendcar.dao.ICollectDao;
import com.taihu.secendcar.dao.impl.CollectDaoImpl;
import com.taihu.secendcar.entity.Collect;
import com.taihu.secendcar.service.ICollectService;

import java.util.LinkedList;
import java.util.List;

/**
 * CollectServiceImpl
 *
 * @author hcj
 * @date 2023-06-29
 */
public class CollectServiceImpl implements ICollectService {

    private ICollectDao collectDao;

    public CollectServiceImpl() {
        collectDao = new CollectDaoImpl();
    }

    /**
     * 根据用户id查询此用户下所有的收藏收藏
    */
    @Override
    public LinkedList<Collect> findCollectCarByUserId(String userId) {
        return collectDao.queryCollectCarByUserId(userId);
    }

    /**
     * 添加收藏车辆信息
    */
    @Override
    public void addCollect(Collect c) {
        collectDao.insertCollect(c);
    }
}
