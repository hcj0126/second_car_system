package com.taihu.secendcar.dao.impl;

import com.taihu.secendcar.constant.BaseConstant;
import com.taihu.secendcar.dao.ICarDao;
import com.taihu.secendcar.entity.Car;
import com.taihu.secendcar.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * CarDaoImpl
 *
 * @author hcj
 * @date 2023-06-27
 */
public class CarDaoImpl implements ICarDao {

    // 创建第三方jar包的DbUtils核心类 QueryRunner对象
    private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

    /**
     * 查询最新10辆二手车信息
    */
    @Override
    public List<Car> findAll() {
        // 创建sql语句
        String sql = "select * from (select * from car where flag =? order by publishDate desc) id limit 0,10";
        // 设置参数
        Object[] params = {BaseConstant.CAR_FLAG_ONE};
        // 查询多条记录 BeanListHandler
        BeanListHandler<Car> blh = new BeanListHandler<>(Car.class);
        List<Car> list = null;
        // 执行查询
        try {
            list = qr.query(sql,blh,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateCar(Car car) {
        // 创建sql语句
        String sql = "update car set flag = ? where id = ?";
        // 设置实际参数
        Object[] params = {BaseConstant.CAR_FLAG_ZERO,car.getId()};
        // 执行
        try {
            qr.update(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 根据车型id查询出车辆对象集合
    */
    @Override
    public List<Car> queryCarByModelId(String modelId) {
        // 创建sql语句
        String sql = "select * from car where modelId = ?";
        // 设置参数
        Object[] param = {modelId};
        // 创建BeanListHandler
        BeanListHandler<Car> blh = new BeanListHandler<>(Car.class);
        List<Car> list = null;
        // 执行
        try {
            list = qr.query(sql,blh,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 根据carId查询车辆信息
     */
    @Override
    public Car queryCollectCarByCarId(String carId) {
        // 创建sql语句
        String sql = "select * from car where id = ?";
        // 设置实际参数
        Object[] param = {carId};
        // 创建BeanHandler
        BeanHandler<Car> bh = new BeanHandler<>(Car.class);
        // 执行
        Car car = null;
        try {
            car = qr.query(sql,bh,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return car;
    }

}
