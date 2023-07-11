package com.taihu.secendcar.service;

import com.taihu.secendcar.entity.User;

/**
 * IUserService
 *
 * @author hcj
 * @date 2023-06-26
 */
public interface IUserService {
    // 根据用户名查询User对象
    User findUserByUsername(String username);

    // 向user表中新增一条数据
    void addUser(User user);

    // 更新user对象
    void updateUserBalance(User user);
}
