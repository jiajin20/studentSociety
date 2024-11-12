package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.dao.StudentDao;
import com.cf.studentsociety.entity.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class StudentDaoImpl extends BaseDao implements StudentDao {
    @Override
    public Student login(String acc, String pwd) throws SQLException {
        QueryRunner qr = new QueryRunner(getDataSource());
        String sql = " select * from student where studentNumber = ? and password = ?";
        return qr.query(sql,new BeanHandler<Student>(Student.class),acc,pwd);
    }
    @Override
    public Student findByNumber(String number) throws SQLException {
        QueryRunner qr = new QueryRunner(getDataSource());
        String sql = "select * from student where studentNumber = ?";
        return qr.query(sql,new BeanHandler<Student>(Student.class),number);
    }
    @Override
    public Student findById(Integer id) throws SQLException {
        QueryRunner qr = new QueryRunner(getDataSource());
        String sql = "select * from student where studentId = ?";
        return qr.query(sql,new BeanHandler<Student>(Student.class),id);
    }


}
