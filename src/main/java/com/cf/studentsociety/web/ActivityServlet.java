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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ActivityServlet", value = "/activity/*")
public class ActivityServlet extends RouteServlet {
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
            handleException(throwables, req, res);
        }
        req.setAttribute("society", society);
        return "activity/addActivity";

    }

    public String addActivity(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String intro = req.getParameter("intro");
        String startStr = req.getParameter("start");
        String endStr = req.getParameter("end");
        String assIdStr = req.getParameter("assId");
        System.out.println(name + intro + startStr + endStr + assIdStr);
        // 时间格式转换
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startLocalDateTime = LocalDateTime.parse(startStr, formatter);
        LocalDateTime endLocalDateTime = LocalDateTime.parse(endStr, formatter);
        // 转换为 Timestamp
        Timestamp start = Timestamp.valueOf(startLocalDateTime);
        Timestamp end = Timestamp.valueOf(endLocalDateTime);
        try {
            Integer assId = Integer.valueOf(assIdStr);
            Activity activity = new Activity();
            activity.setActivity_societyId(assId);
            activity.setActivity_name(name);
            activity.setActivity_intro(intro);
            activity.setActivity_start_time(start);
            activity.setActivity_end_time(end);
            System.out.println(activity);
            Integer result = activityDao.addActivity(activity);
            req.setAttribute("result", result);

        } catch (DateTimeParseException | NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("result", -2); // 参数格式错误
        } catch (SQLException e) {
            e.printStackTrace();
            handleException(e, req, res);
        }

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
            handleException(throwables, req, res);
        }
        req.setAttribute("society", society);
        req.setAttribute("activities", activities);
        return "activity/activityList";

    }

    public Object updateActivity(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String intro = req.getParameter("intro");
        Integer id = Integer.valueOf(idStr);
        Integer result = null;
        try {
            result = activityDao.updateIntro(id, intro);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            handleException(throwables, req, res);
        }
        return result;
    }
}
