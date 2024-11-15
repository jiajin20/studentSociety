package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.ActivityDao;
import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.entity.Activity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ActivityDaoImpl extends BaseDao implements ActivityDao {

    @Override
    public Integer addActivity(Activity activity) throws SQLException {
        String sql = "insert into activity value (null,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.update(sql,activity.getActivity_societyId(),activity.getActivity_name(),activity.getActivity_intro(),activity.getActivity_start_time(),activity.getActivity_end_time());
    }
    @Override
    public List<Activity> queryBySocietyId(Integer societyId) throws SQLException {
        String sql = "select * from Activity where activity_societyId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.query(sql, new BeanListHandler<Activity>(Activity.class),societyId);
    }

}

