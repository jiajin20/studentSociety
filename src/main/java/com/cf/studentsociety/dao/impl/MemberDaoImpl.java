package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.dao.MemberDao;

import com.cf.studentsociety.entity.Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class MemberDaoImpl extends BaseDao implements MemberDao {

    private Object socityId;

    @Override
    public Integer addMember(Member member) throws SQLException {
        String sql = "insert into member (memberStudentId, joinDate,member_societyId) values(?,?,?)";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.update(sql, member.getMemberStudentId(), member.getJoinDate(),member.getMember_societyId());
    }

    @Override
    public Integer isMemberIn(Member member) throws SQLException {
        String sql = "select count(1) from member where member_societyId = ? and memberStudentId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.query(sql, new ScalarHandler<Long>(), member.getMember_societyId(), member.getMemberStudentId()).intValue();
    }

    @Override
    public List<Member> queryAllMember(Integer status) throws SQLException {
        String sql = "select * from member where memberStatus = ? and member_socityId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.query(sql, new BeanListHandler<>(Member.class),status,socityId);
    }
}
