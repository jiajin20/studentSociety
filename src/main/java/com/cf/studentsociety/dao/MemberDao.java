package com.cf.studentsociety.dao;

import com.cf.studentsociety.entity.Member;

import java.sql.SQLException;
import java.util.List;


public interface MemberDao {
    Integer addMember(Member member) throws SQLException;
    Integer isMemberIn(Member member) throws SQLException;
    List<Member> queryAllMember(Integer status,Integer societyId) throws SQLException;
    Integer deleteGraduated(Integer assId) throws SQLException;
    Integer deleteMember(Integer id, Integer status) throws SQLException;
    Member queryByNumber(String number) throws SQLException;

}
