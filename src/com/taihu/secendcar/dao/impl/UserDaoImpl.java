package com.taihu.secendcar.dao.impl;

import com.taihu.secendcar.dao.IUserDao;
import com.taihu.secendcar.entity.User;
import com.taihu.secendcar.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * UserDaoImpl
 *   持久层：操作数据库的
 * @author hcj
 * @date 2023-06-26
 */
public class UserDaoImpl implements IUserDao {

    // 创建第三方jar包的DbUtils核心类 QueryRunner对象
    private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public User queryUserByUsername(String username) {
        // 创建sql语句  ？：表示占位符，预处理对象
        String sql = "select * from user where username=?";
        // 设置实际参数
        Object[] param = {username};
        // 查询一条记录，用BeanHandler
        BeanHandler<User> bh = new BeanHandler<>(User.class);
        User user = null;
        // 捕获异常用快捷键 Alt+Enter
        try {
            // 执行查询query
            user= qr.query(sql,bh,param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    /**
     * 新用户注册，向user表中新增一条数据
    */
    @Override
    public void insertUser(User u) {
        // 创建sql语句
        String sql = "insert into user (id,username,password,balance)values(?,?,?,?)";
        // 设置实际参数
        Object[] params = {u.getId(),u.getUsername(),u.getPassword(),u.getBalance()};
        // 执行插入update
        try {
            qr.update(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 更新user对象
    */
    @Override
    public void updateUserBalance(User user) {
        String sql = "update user set balance = ? where id = ?";
        // 设置实际参数
        Object[] params = {user.getBalance(),user.getId()};
        // 执行 update
        try {
            qr.update(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
