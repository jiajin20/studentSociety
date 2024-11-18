package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.dao.StudentDao;
import com.cf.studentsociety.entity.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





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
    @Override
    public List<Student> queryStudent(String name, String number) throws SQLException {
        StringBuffer buffer = new StringBuffer("select * from student where 1 = 1");
        QueryRunner qr = new QueryRunner(getDataSource());

        // 构建查询条件和参数
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            buffer.append(" and studentName like ?");
            params.add("%" + name + "%");
        }

        if (number != null && !number.isEmpty()) {
            buffer.append(" and studentNumber like ?");
            params.add("%" + number + "%");
        }

        // 执行查询
        List<Student> students = null;
        if (params.isEmpty()) {
            // 如果没有传入任何参数，查询所有学生
            students = qr.query(buffer.toString(), new BeanListHandler<>(Student.class));
        } else {
            // 使用正确的参数执行查询
            students = qr.query(buffer.toString(), new BeanListHandler<>(Student.class), params.toArray());
        }

        return students;
    }

}
