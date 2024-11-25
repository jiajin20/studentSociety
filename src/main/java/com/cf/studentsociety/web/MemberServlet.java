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
    private StudentDao studentDao = new StudentDaoImpl();

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

    public Object members(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Member> memberList = null;
        String statusStr = req.getParameter("status");
        Integer status = Integer.valueOf(statusStr);
        addPage(req, res);
        Society soc = (Society)req.getAttribute("society");
        Integer societyId = ((Society)req.getAttribute("society")).getSocietyId();
        System.out.println(soc);
        System.out.println(societyId);
        System.out.println(status);
        try {
            memberList = memberDao.queryAllMember(status,societyId);
            System.out.println(memberList);
            memberList = memberList.stream().map(member -> {
                Student stu = null;
                try {
                    stu = studentDao.findById(member.getMemberStudentId());
                    System.out.println(member.getMemberStudentId());
                    member.setMemberInfo(stu);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return member;
            }).collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        req.setAttribute("members",memberList);
        return status == 1 ? "member/memberList" : "member/quitList";
    }

    public Object deleteGraduated (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String assIdStr = req.getParameter("assId");
        Integer assId = Integer.valueOf(assIdStr);
        Integer result = null;
        try {
            result = memberDao.deleteGraduated(assId);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        return result;
    }

    public Object deleteMember (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String idStr = req.getParameter("id");
        String statusStr = req.getParameter("status");
        Integer id = Integer.parseInt(idStr);
        Integer status = Integer.valueOf(statusStr);
        System.out.println(id+" "+status);
        Integer result = null;
        try {
            result = memberDao.deleteMember(id,status);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        return result;
    }
}