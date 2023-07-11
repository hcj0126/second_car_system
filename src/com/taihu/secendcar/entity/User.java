package com.taihu.secendcar.entity;

import java.util.LinkedList;

/**
 * User
 *  用户实体类，对应数据库user表
 * @author hcj
 * @date 2023-06-25
 */
public class User {
    // 属性 Feild
    private String id; // 用户编号
    private String username; // 用户名
    private String password; // 密码
    private Double balance; // 用户余额
    private Integer isAdmin; // 是否是管理员 0:不是 1:是

    // 非数据库字段  对比车辆集合
    private LinkedList<Car> compareCarList;

    // 构造方法 Constructor 用于初始化对象
    public User() { // 无参构造方法
        compareCarList = new LinkedList<>();
    }
    // 全参构造方法
    public User(String id, String username, String password, Double balance, Integer isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }
    // 生成getter/setter方法，Method  用于取值和赋值
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public LinkedList<Car> getCompareCarList() {
        return compareCarList;
    }

    public void setCompareCarList(LinkedList<Car> compareCarList) {
        this.compareCarList = compareCarList;
    }

    // 对象格式化输出
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
