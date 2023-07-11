package com.taihu.secendcar.dao;

import com.taihu.secendcar.entity.Car;

import java.util.List;

/**
 * ICarDao
 *
 * @author hcj
 * @date 2023-06-27
 */
public interface ICarDao {
    // 查询最新10辆二手车信息
    List<Car> findAll();

    // 更新car信息
    void updateCar(Car car);

    // 根据车型id查询出车辆对象集合
    List<Car> queryCarByModelId(String modelId);

    // 根据carId查询车辆信息
    Car queryCollectCarByCarId(String carId);
}
