package com.cf.studentsociety.web;

import com.cf.studentsociety.dao.MemberDao;
import com.cf.studentsociety.dao.SocietyDao;
import com.cf.studentsociety.dao.StudentDao;
import com.cf.studentsociety.dao.impl.MemberDaoImpl;
import com.cf.studentsociety.dao.impl.SocietyDaoImpl;
import com.cf.studentsociety.dao.impl.StudentDaoImpl;
import com.cf.studentsociety.entity.Member;
import com.cf.studentsociety.entity.Society;
import com.cf.studentsociety.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="memberServlet",value = "/member/*")
public class MemberServlet extends RouteServlet{
    private SocietyDao societyDao = new SocietyDaoImpl();

    private MemberDao memberDao = new MemberDaoImpl();
    @Override
    protected Class getMyClass() {
        return this.getClass();
    }
    public Object addPage (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String number = (String)session.getAttribute("student");
        Society society = null;
        try {
            society = societyDao.queryByStudentNumber(number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        req.setAttribute("society",society);
        return "member/addStudent";
    }
    public Object addMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
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
            if(i > 0){
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("message","该生已加入");
                return map;
            }
            result = memberDao.addMember(member);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        return result;
    }

    public Object members (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        return null;
    }

    public Object deleteGraduated (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        return null;
    }

    public Object deleteMember (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        return null;
    }
}