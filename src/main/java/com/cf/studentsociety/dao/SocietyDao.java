package com.cf.studentsociety.dao;
import com.cf.studentsociety.entity.Society;

import java.sql.SQLException;
import java.util.List;

public interface SocietyDao {
    Integer insertSociety(Society society) throws SQLException;
    List<Society> getAllSociety() throws SQLException;
    Integer updateSociety(Society society) throws SQLException;
    Society queryByStudentNumber(String number) throws SQLException;
}
