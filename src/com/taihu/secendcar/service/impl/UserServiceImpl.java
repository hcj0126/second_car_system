package com.taihu.secendcar.service.impl;

import com.taihu.secendcar.dao.IUserDao;
import com.taihu.secendcar.dao.impl.UserDaoImpl;
import com.taihu.secendcar.entity.User;
import com.taihu.secendcar.service.IUserService;

/**
 * UserServiceImpl
 *  业务处理层：写业务逻辑的
 *   @Override 重写
 *   @Overload 重载
 * @author hcj
 * @date 2023-06-26
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    public UserServiceImpl() {
        // 使用多态创建对象 向上转型/向上造型  父类的引用变量指向子类对象
        userDao = new UserDaoImpl();
    }

    /**
     * 根据用户名查询User对象
     * 如果返回的user对象不为空，说明此用户名已被占用
    */
    @Override
    public User findUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    /**
     * 向user表中新增一条数据
    */
    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUserBalance(User user) {
        userDao.updateUserBalance(user);
    }
}
