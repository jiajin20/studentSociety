package com.cf.studentsociety.web;

import com.cf.studentsociety.dao.ActivityDao;
import com.cf.studentsociety.dao.SocietyDao;
import com.cf.studentsociety.dao.impl.ActivityDaoImpl;
import com.cf.studentsociety.dao.impl.SocietyDaoImpl;
import com.cf.studentsociety.entity.Activity;
import com.cf.studentsociety.entity.Society;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ActivityServlet", value = "/activity/*")
public class ActivityServlet extends RouteServlet{
    private SocietyDao societyDao = new SocietyDaoImpl();
    private ActivityDao activityDao = new ActivityDaoImpl();

    @Override
    protected Class getMyClass() {
        return this.getClass();
    }
    public String addPage(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String number = (String) session.getAttribute("student");
        Society society = null;
        try {
            society = societyDao.queryByStudentNumber(number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        req.setAttribute("society", society);
        return "activity/addActivity";

    }

    public String addActivity(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String intro = req.getParameter("intro");
        String startStr = req.getParameter("start").replaceAll("T"," ");
        String endStr = req.getParameter("end").replaceAll("T"," ");
        String assIdStr = req.getParameter("assId");
        Integer assId = Integer.valueOf(assIdStr);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date start = null;
        Date end = null;
        try {
            start = format.parse(startStr);
            end = format.parse(endStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer result = null;
        if(!start.after(new Date())){
            result = 0;
        }else{Activity activity = new Activity();
            activity.setActivity_societyId(assId);
            activity.setActivity_name(name);
            activity.setActivity_intro(intro);
            activity.setActivity_start_time(start);
            activity.setActivity_end_time(end);
            try {
                result = activityDao.addActivity(activity);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                handleException(throwables,req,res);
            }
        }
        req.setAttribute("result", result);
        return "activity/addActivity";



    }

    public String activityList(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String number = (String) session.getAttribute("student");
        Society society = null;
        List<Activity> activities = null;
        try {
            society = societyDao.queryByStudentNumber(number);
            activities = activityDao.queryBySocietyId(society.getSocietyId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables,req,res);
        }
        req.setAttribute("society", society);
        req.setAttribute("activities", activities);
        return "activity/activityList";

    }

    public Object updateActivity(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        return null;
    }

}
