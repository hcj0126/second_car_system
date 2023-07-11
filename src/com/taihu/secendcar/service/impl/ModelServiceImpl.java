package com.taihu.secendcar.service.impl;

import com.taihu.secendcar.dao.IModelDao;
import com.taihu.secendcar.dao.impl.ModelDaoImpl;
import com.taihu.secendcar.entity.Model;
import com.taihu.secendcar.service.IModelService;

import java.util.List;

/**
 * ModelServiceImpl
 *
 * @author hcj
 * @date 2023-06-27
 */
public class ModelServiceImpl implements IModelService {

    private IModelDao modelDao;

    public ModelServiceImpl() {
        modelDao = new ModelDaoImpl();
    }

    @Override
    public Model findModelById(String id) {
        return modelDao.queryModelById(id);
    }

    @Override
    public List<Model> findModelByBrandId(String brandId) {
        return modelDao.queryModelByBrandId(brandId);
    }
}
