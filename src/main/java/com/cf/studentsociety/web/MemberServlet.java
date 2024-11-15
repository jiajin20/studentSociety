package com.cf.studentsociety.web;

import com.cf.studentsociety.dao.MemberDao;
import com.cf.studentsociety.dao.StudentDao;
import com.cf.studentsociety.dao.impl.MemberDaoImpl;
import com.cf.studentsociety.dao.impl.StudentDaoImpl;
import com.cf.studentsociety.entity.Member;
import com.cf.studentsociety.entity.Society;
import com.cf.studentsociety.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "memberServlet",value = "/member/*")

public class MemberServlet extends RouteServlet{

    private MemberDao memberDao = new MemberDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    protected Class getMyClass(){
        return this.getClass();
    }

    public Object addPage(HttpServletRequest req, HttpServletResponse res) throws
            ServerCloneException, IOException {
        return null;
    }

    public Object addMember(HttpServletRequest req, HttpServletResponse res) throws
            ServerCloneException, IOException, ServletException {

        String stuIdStr = req.getParameter("stuId");
        String assIdStr = req.getParameter("assId");
        Integer stuId = Integer.valueOf(stuIdStr);
        Integer assId = Integer.valueOf(assIdStr);
        Member member = new Member();
        member.setMember_societyId(String.valueOf(assId));
        member.setMemberStudentId(stuId);
        member.setJoinDate(new Date());

        Integer result = null;
        try {
            int i = memberDao.isMemberIn(member);
            if (i>0){
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("message","该生已加入");
                return map;
            }
            result = memberDao.addMember(member);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        return result;
    }

    public Object members(HttpServletRequest req, HttpServletResponse res) throws
            ServerCloneException, IOException, ServletException {

        List<Member> memberList = null;
        String statusStr = req.getParameter("status");
        Integer status = Integer.valueOf(statusStr);
        addPage(req,res);
        Integer socityId =
                ((Society)req.getSession().getAttribute("society")).getSocietyId();
        try {
            memberList = memberDao.queryAllMember(status);
            memberList = memberList.stream().map(member -> {
                Student stu = null;
                try {
                    // 根据成员的学生ID查询学生信息
                    stu = studentDao.findById(member.getMemberStudentId());
                    // 将学生信息设置到成员对象中
                    member.setMemberInfo(stu);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return member;
            }).collect(Collectors.toList());

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables, req, res);
        }
        req.setAttribute("member",memberList);
        return status == 1 ? "member/memberList" : "member/quitList";


    }

    public Object deleteGraduated(HttpServletRequest req, HttpServletResponse res) throws
            ServerCloneException, IOException {
        return null;
    }

    public Object deleteMember(HttpServletRequest req, HttpServletResponse res) throws
            ServerCloneException, IOException {
        return null;
    }

}
