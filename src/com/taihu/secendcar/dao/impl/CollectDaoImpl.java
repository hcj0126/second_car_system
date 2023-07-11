package com.taihu.secendcar.dao.impl;

import com.taihu.secendcar.dao.ICollectDao;
import com.taihu.secendcar.entity.Car;
import com.taihu.secendcar.entity.Collect;
import com.taihu.secendcar.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * CollectDaoImpl
 *
 * @author hcj
 * @date 2023-06-29
 */
public class CollectDaoImpl implements ICollectDao {

    // 创建第三方jar包的DbUtils核心类 QueryRunner对象
    private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

    /**
     * 根据用户id查询此用户下所有的收藏收藏
    */
    @Override
    public LinkedList<Collect> queryCollectCarByUserId(String userId) {
        // 创建sql语句
        String sql = "select * from collect where userId = ?";
        // 设置实际参数
        Object[] param = {userId};
        // 创建BeanListHandler
        BeanListHandler<Collect> blh = new BeanListHandler<>(Collect.class);
        LinkedList<Collect> list = new LinkedList<>();
        // 执行
        try {
            for (Collect c : qr.query(sql,blh,param)) {
                list.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 添加收藏车辆信息
     */
    @Override
    public void insertCollect(Collect c) {
        // 创建sql语句
        String sql = "insert into collect(id,carId,userId,createDate)values(?,?,?,?)";
        // 设置实际参数
        Object[] params = {c.getId(),c.getCarId(),c.getUserId(),c.getCreateDate()};
        // 执行
        try {
            qr.update(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
