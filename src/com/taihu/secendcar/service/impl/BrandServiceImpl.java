package com.taihu.secendcar.service.impl;

import com.taihu.secendcar.dao.IBrandDao;
import com.taihu.secendcar.dao.impl.BrandDaoImpl;
import com.taihu.secendcar.entity.Brand;
import com.taihu.secendcar.service.IBrandService;

import java.util.List;

/**
 * BrandServiceImpl
 *
 * @author hcj
 * @date 2023-06-27
 */
public class BrandServiceImpl implements IBrandService {

    private IBrandDao brandDao;

    public BrandServiceImpl() {
        brandDao = new BrandDaoImpl();
    }

    @Override
    public Brand findBrandById(String id) {
        return brandDao.queryBrandById(id);
    }

    @Override
    public List<Brand> findBrandAll() {
        return brandDao.queryBrandAll();
    }
}
