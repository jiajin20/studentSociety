package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.dao.StudentDao;
import com.cf.studentsociety.entity.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
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
    public List<Student> queryStudent(String name, String number)throws SQLException {
        StringBuffer buffer = new StringBuffer("select * from student where 1 = 1");
        QueryRunner qr = new QueryRunner(getDataSource());
        if (name!=null && number !=""){
            buffer.append("and studentNumber like concat('%'?'%')");
        }else if (number !=null && number !=""){
            buffer.append("and studentNumber like concat('%'?'%')");
        }

        List<Student>students = null;
        if (name != null && name!="" &&(number == null || number == "")){
            students = qr.query(buffer.toString(), new BeanListHandler<Student>(Student.class),name);
        } else if (number != null && number!="" &&(name == null || name == "")) {
            students = qr.query(buffer.toString(), new BeanListHandler<Student>(Student.class),number);
        } else if (name != null && name!="" &&(number == null || number == "")) {
            students = qr.query(buffer.toString(), new BeanListHandler<Student>(Student.class),name,number);
        }else {
            students = qr.query(buffer.toString(), new BeanListHandler<Student>(Student.class));
        }
        return students;
    }

}
