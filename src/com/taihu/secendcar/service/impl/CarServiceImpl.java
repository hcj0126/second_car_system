package com.taihu.secendcar.service.impl;

import com.taihu.secendcar.dao.ICarDao;
import com.taihu.secendcar.dao.impl.CarDaoImpl;
import com.taihu.secendcar.entity.Car;
import com.taihu.secendcar.service.ICarService;

import java.util.List;

/**
 * CarServiceImpl
 *
 * @author hcj
 * @date 2023-06-27
 */
public class CarServiceImpl implements ICarService {

    private ICarDao carDao;

    public CarServiceImpl() {
        carDao = new CarDaoImpl();
    }

    /**
     * 查询最新10辆二手车信息
     */
    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    /**
     * 根据车型id查询出车辆对象集合
    */
    @Override
    public List<Car> findCarByModelId(String modelId) {
        return carDao.queryCarByModelId(modelId);
    }

    /**
     * 根据carId查询车辆信息
     */
    @Override
    public Car findCollectCarByCarId(String carId) {
        return carDao.queryCollectCarByCarId(carId);
    }
}
