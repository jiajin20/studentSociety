package com.cf.studentsociety.dao;

import com.cf.studentsociety.entity.Activity;

import java.sql.SQLException;
import java.util.List;

public interface ActivityDao {
    Integer addActivity(Activity activity)throws SQLException;
    List<Activity> queryBySocietyId(Integer societyId)throws SQLException;
    Integer updateIntro(Integer id,String intro) throws SQLException;
}
