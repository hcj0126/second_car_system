package com.taihu.secendcar.dao.impl;

import com.taihu.secendcar.dao.IModelDao;
import com.taihu.secendcar.entity.Car;
import com.taihu.secendcar.entity.Model;
import com.taihu.secendcar.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * ModelDaoImpl
 *
 * @author hcj
 * @date 2023-06-27
 */
public class ModelDaoImpl implements IModelDao {

    // 创建第三方jar包的DbUtils核心类 QueryRunner对象
    private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

    /**
     * 根据车型id查询一条车型数据
    */
    @Override
    public Model queryModelById(String id) {
        // 创建sql语句
        String sql = "select * from model where id = ?";
        // 设置参数
        Object[] param = {id};
        // 创建BeanHandler
        BeanHandler<Model> bh = new BeanHandler<>(Model.class);
        // 执行查询
        Model model = null;
        try {
            model = qr.query(sql,bh,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return model;
    }

    /**
     * 根据brandId到model表中查询Model对象集合
    */
    @Override
    public List<Model> queryModelByBrandId(String brandId) {
        // 创建sql语句
        String sql = "select * from model where brandId = ?";
        // 设置实际参数
        Object[] param = {brandId};
        // 创建BeanListHandler对象
        BeanListHandler<Model> blh = new BeanListHandler<>(Model.class);
        List<Model> list = null;
        // 执行
        try {
            list = qr.query(sql,blh,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
