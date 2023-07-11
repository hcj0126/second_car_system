package com.taihu.secendcar.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DruidUtils
 *   Druid数据库连接池的工具类
 *      1.加载驱动
 *      2.获取连接
 *      3.释放资源
 * @author hcj
 * @date 2023-06-25
 */
public class DruidUtils {

    // 创建连接池对象
    private static DataSource dataSource;

    // 静态代码块，只要用到此类，必先执行static代码块,加载jdbc.properties获取数据源(连接池)
    static{
        try {
            // IO流：读取文件   InputStream:字节输入流，用于从文件中读取数据
            InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 创建集合Properties对象
            Properties prop = new Properties();
            // 调用Properties集合中自带的方法load()加载数据
            prop.load(is);
            // 利用DruidDataSourceFactory获取数据源(连接池)
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接池(数据源)
    */
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * 获取连接
    */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection(); // 此连接从数据库连接池中获取
    }
    /**
     * 释放资源(归还连接)
     */
    public static void closeResource(ResultSet rs, PreparedStatement ps,Connection con){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
