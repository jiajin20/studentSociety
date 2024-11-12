package com.cf.studentsociety.dao;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class BaseDao {
    private static DruidDataSource dataSource = new DruidDataSource();
    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/student_society?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        dataSource.setMaxActive(20);  // 最大存活数量
        dataSource.setInitialSize(1); // 初始连接数量
        dataSource.setMaxWait(60000); // 最长等待时间
        dataSource.setMinIdle(1);     // 最小等待数量

        dataSource.setTestWhileIdle(true);  // 检测连接是否有效，如果超过默认时间则检测一次
    }

    protected DataSource getDataSource(){
        return dataSource;
    }

}
