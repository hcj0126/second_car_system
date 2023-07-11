package com.taihu.secendcar.dao;

import com.taihu.secendcar.entity.User;

/**
 * IUserDao
 *
 * @author hcj
 * @date 2023-06-26
 */
public interface IUserDao {
    // 根据用户名查询User对象
    User queryUserByUsername(String username);

    // 向user表中新增一条数据
    void insertUser(User user);

    // 更新user对象
    void updateUserBalance(User user);
}
