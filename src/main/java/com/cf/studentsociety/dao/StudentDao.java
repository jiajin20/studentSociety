package com.cf.studentsociety.dao;

import com.cf.studentsociety.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao{
    public Student login(String acc, String pwd) throws SQLException;
    public Student findByNumber(String number) throws SQLException;
    public Student findById(Integer id) throws SQLException;
    List<Student> queryStudent(String name, String number)throws SQLException;

}
