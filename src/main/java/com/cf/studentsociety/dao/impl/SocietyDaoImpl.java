package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.dao.SocietyDao;
import com.cf.studentsociety.entity.Society;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class SocietyDaoImpl extends BaseDao implements SocietyDao {
    @Override
    public Integer insertSociety(Society society) throws SQLException {
        QueryRunner qr = new QueryRunner(getDataSource());
        String sql = "insert into society value (null,?,?,?,?,?)";
        return qr.update(sql,society.getSociety_creator(), society.getSociety_create_date(),society.getSociety_name(), society.getSociety_intro(),society.getSociety_status());
    }
    @Override
    public List<Society> getAllSociety() throws SQLException {
        QueryRunner qr = new QueryRunner(getDataSource());
        String sql = "select * from society";
        return qr.query(sql,new BeanListHandler<Society>(Society.class));
    }
    @Override
    public Integer updateSociety(Society society) throws SQLException {
        StringBuffer buffer = new StringBuffer("update society set  ");
        QueryRunner qr = new QueryRunner(getDataSource());
        int result = 0;
        if(society.getSociety_name() != null){
            buffer.append(" society_name = ? ").append("where societyId = ?");
            result = qr.update(buffer.toString(),society.getSociety_name(),society.getSocietyId());
        }else if (society.getSociety_intro() != null){
            buffer.append(" society_intro = ? ").append("where societyId = ?");
            result = qr.update(buffer.toString(),society.getSociety_intro(),society.getSocietyId());
        }else if (society.getSociety_status() != null){
            buffer.append(" society_status = ? ").append("where societyId = ?");
            result = qr.update(buffer.toString(),society.getSociety_status(),society.getSocietyId());
        }
        return result;
    }

}
