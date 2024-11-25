package com.cf.studentsociety.dao.impl;

import com.cf.studentsociety.dao.BaseDao;
import com.cf.studentsociety.dao.MemberDao;

import com.cf.studentsociety.entity.Member;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
public class MemberDaoImpl extends BaseDao implements MemberDao {
    private Object id;

    @Override
    public Integer addMember(Member member) throws SQLException {
        String sql = "insert into member (memberStudentId,joinDate,member_societyId) value (?,?,?)";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.update(sql,member.getMemberStudentId(),member.getJoinDate(),member.getMember_societyId());
    }
    @Override
    public Integer isMemberIn(Member member) throws SQLException {
        String sql = "select count(1) from member where member_societyId = ? and memberStudentId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.query(sql, new ScalarHandler<Long>(), member.getMember_societyId(), member.getMemberStudentId()).intValue();
    }
    @Override
    public List<Member> queryAllMember(Integer status,Integer societyId) throws SQLException {
        String sql = "select * from member where memberStatus = ? and member_societyId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.query(sql,new BeanListHandler<Member>(Member.class),status,societyId);
    }
    @Override
    public Integer deleteGraduated(Integer assId) throws SQLException {
        String sql = "update member,student set member.memberStatus = 2 where member.member_societyId = ? and member.memberStudentId = student.studentId and student.graduated = 2";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.update(sql, assId);
    }

    @Override
    public Integer deleteMember(Integer id ,Integer status) throws SQLException {
        String sql = "update member set member.memberStatus = ? where memberId = ?";
        QueryRunner qr = new QueryRunner(getDataSource());
        return qr.update(sql,status,id);
    }

    @Override
    public Member queryByNumber(String number) throws SQLException {
        // 正确的SQL语句，使用INNER JOIN来连接两个表，并且使用正确的字段引用方式
        String sql = "SELECT m.* FROM member m INNER JOIN student s ON m.memberStudentId = s.studentId WHERE s.studentNumber = ?";

        // 使用QueryRunner对象执行查询
        QueryRunner qr = new QueryRunner(getDataSource());

        // 返回查询结果，BeanHandler将结果集转换为Member对象
        return qr.query(sql, new BeanHandler<Member>(Member.class), number);
    }

}