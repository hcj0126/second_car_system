package com.taihu.secendcar.app;

import com.taihu.secendcar.controller.CarSystemController;

/**
 * CarSystemApp
 *  二手车系统的启动类
 * @author hcj
 * @date 2023-06-25
 */
public class CarSystemApp {
    public static void main(String[] args) {
        // 创建CarSystemController对象
        CarSystemController csc = new CarSystemController();
        // 调用运行开启系统看到主菜单的方法
        csc.startSystem();
    }
}
