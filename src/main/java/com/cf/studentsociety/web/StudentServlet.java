package com.cf.studentsociety.web;

import com.cf.studentsociety.dao.StudentDao;
import com.cf.studentsociety.dao.impl.StudentDaoImpl;
import com.cf.studentsociety.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "student", urlPatterns = "/student/*")
public class StudentServlet extends RouteServlet {

    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    protected Class getMyClass() {
        return this.getClass();
    }
    public String login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        Student stu = null;
        try {
            stu = studentDao.login(account,password);
        } catch (SQLException throwables) {
            handleException(throwables,req,res);
        }
        if(stu != null){
            HttpSession session = req.getSession();
            session.setAttribute("student",stu.getStudentNumber());
//            res.sendRedirect("/studentSociety/main.jsp");
            res.sendRedirect("/studentSociety/society/mainIndex");
        }else{
            req.getRequestDispatcher("/index.jsp?message=account or password is wrong").forward(req,res);
        }
        return "direct";
    }
    public String queryStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        List<Student> students = null;
        try {
            students = studentDao.queryStudent(name, number);
            req.setAttribute("students", students);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            req.setAttribute("exception", throwables);
            return "error";
        }
        req.getRequestDispatcher("/member/addPage").forward(req, res);
        return "null";
    }
}
