package com.taihu.secendcar.dao.impl;

import com.taihu.secendcar.dao.IBrandDao;
import com.taihu.secendcar.entity.Brand;
import com.taihu.secendcar.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * BrandDaoImpl
 *
 * @author hcj
 * @date 2023-06-27
 */
public class BrandDaoImpl implements IBrandDao {

    // 创建第三方jar包的DbUtils核心类 QueryRunner对象
    private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

    /**
     * 根据品牌id查询一条品牌数据
    */
    @Override
    public Brand queryBrandById(String id) {
        // 创建sql语句
        String sql = "select * from brand where id = ?";
        // 设置参数
        Object[] param = {id};
        // 创建BeanHandler
        BeanHandler<Brand> bh = new BeanHandler<>(Brand.class);
        // 执行查询
        Brand brand = null;
        try {
            brand = qr.query(sql,bh,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return brand;
    }

    /**
     * 查询所有的品牌集合
    */
    @Override
    public List<Brand> queryBrandAll() {
        // 创建sql语句
        String sql = "select * from brand";
        // 创建BeanListHandler
        BeanListHandler<Brand> blh = new BeanListHandler<>(Brand.class);
        List<Brand> list = null;
        try {
            list = qr.query(sql,blh);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
