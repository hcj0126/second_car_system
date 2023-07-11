package com.taihu.secendcar.entity;

import java.util.ArrayList;

/**
 * CompareInfo
 *  存入对比车辆信息的类
 * @author hcj
 * @date 2023-06-30
 */
public class CompareInfo {
    private ArrayList<Object> title;

    public CompareInfo() {
        title = new ArrayList<>();
    }

    public ArrayList<Object> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<Object> title) {
        this.title = title;
    }
}
