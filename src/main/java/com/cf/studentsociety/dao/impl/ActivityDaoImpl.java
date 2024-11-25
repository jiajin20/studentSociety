package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.ActivityDao;
import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.entity.Activity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ActivityDaoImpl extends BaseDao implements ActivityDao {

    @Override
    public Integer addActivity(Activity activity) throws SQLException {
        QueryRunner qr = new QueryRunner(getDataSource());
        String sql = "INSERT INTO activity (activity_societyId, activity_name, activity_intro, activity_start_time, activity_end_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            int rowsAffected = qr.update(conn, sql, activity.getActivity_societyId(), activity.getActivity_name(), activity.getActivity_intro(), activity.getActivity_start_time(), activity.getActivity_end_time());

            conn.commit();
            return rowsAffected;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Activity> queryBySocietyId(Integer societyId) throws SQLException {
        String sql = "select * from activity where activity_societyId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.query(sql, new BeanListHandler<Activity>(Activity.class),societyId);
    }
    @Override
    public Integer updateIntro(Integer id, String intro) throws SQLException {
        String sql = "update activity set activity_intro = ? where activityId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.update(sql,intro,id);
    }


}

