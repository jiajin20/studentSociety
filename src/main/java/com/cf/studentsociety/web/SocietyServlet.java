//package com.cf.studentsociety.web;
//
//import com.cf.studentsociety.dao.SocietyDao;
//import com.cf.studentsociety.dao.StudentDao;
//import com.cf.studentsociety.dao.impl.SocietyDaoImpl;
//import com.cf.studentsociety.dao.impl.StudentDaoImpl;
//import com.cf.studentsociety.entity.Society;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@WebServlet(name = "SocietyServlet", value = "/society/*")
////@WebServlet(name = "SocietyServlet", urlPatterns = "/SocietyServlet/*")
//public class SocietyServlet extends RouteServlet {
//    private SocietyDao societyDao = new SocietyDaoImpl();
//    private StudentDao studentDao = new StudentDaoImpl();
//
//    @Override
//    protected java.lang.Class getMyClass() {
//        return this.getClass();
//    }
//
//    // @Override
//    public String mainIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//        List<Society> list = null;
//        try {
//            list = societyDao.getAllSociety();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            handleException(throwables, request, response);
//        }
//        list = list.stream().map(i -> {
//            try {
//                i.setCreator(studentDao.findById(i.getSociety_creator()));
//            } catch (SQLException e) {
//                request.setAttribute("exception", e);
//            }
//            return i;
//        }).collect(Collectors.toList());
//        request.setAttribute("societies", list);
//        return "mainIndex";
//        //   request.getRequestDispatcher("/WEB-INF/jsp/mainIndex.jsp").forward(request,response);
//    }
//
//    public String addSociety(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
//        String name = req.getParameter("name");
//        String intro = req.getParameter("intro");
//        Society society = new Society();
//        society.setSociety_intro(intro);
//        society.setSociety_name(name);
//        society.setSociety_status(1);
//        String number = (String) req.getSession().getAttribute("student");
//        try {
//            society.setSociety_creator(studentDao.findByNumber(number).getStudentId());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            handleException(throwables, req, res);
//        }
//        society.setSociety_create_date(new Date());
//        int result = -1;
//        try {
//            result = societyDao.insertSociety(society);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            handleException(throwables, req, res);
//        }
//        res.sendRedirect("/studentSociety/society/mainIndex");
//        return "direct";
//    }
//
//    public Object updateSociety(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        String idStr = req.getParameter("id");
//        String name = req.getParameter("name");
//        String intro = req.getParameter("intro");
//        Integer id = idStr != null ? Integer.valueOf(idStr) : null;
//        Society society = new Society();
//        society.setSocietyId(id);
//        society.setSociety_name(name);
//        society.setSociety_intro(intro);
//        Map<String, Object> map = new HashMap<String, Object>();
//        Integer result = null;
//        if (id == null) {
//            map.put("message", "id错误");
//        } else {
//            try {
//                result = societyDao.updateSociety(society);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                map.put("message", throwables);
//            }
//            map.put("message", result);
//        }
//        return map;
//    }
//
//    public Object changeStatus(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        String idStr = req.getParameter("id");
//        String statusStr = req.getParameter("status");
//        Integer id = Integer.valueOf(idStr);
//        Integer status = Integer.parseInt(statusStr);
//        Society society = new Society();
//        society.setSocietyId(id);
//        society.setSociety_status(status);
//        int result = -1;
//        try {
//            result = societyDao.updateSociety(society);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            req.setAttribute("exception", throwables);
//            return "error";
//        }
//        if (result == 1) {
//            return result;
//        } else {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("message", "错误修改");
//            return map;
//        }
//    }
//
//}

package com.cf.studentsociety.web;

import com.cf.studentsociety.dao.SocietyDao;
import com.cf.studentsociety.dao.StudentDao;
import com.cf.studentsociety.dao.impl.SocietyDaoImpl;
import com.cf.studentsociety.dao.impl.StudentDaoImpl;
import com.cf.studentsociety.entity.Society;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "SocietyServlet", value = "/society/*")
public class SocietyServlet extends RouteServlet {
    private SocietyDao societyDao = new SocietyDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    protected Class<?> getMyClass() {
        return this.getClass();
    }

    public String mainIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Society> list;
        try {
            list = societyDao.getAllSociety();
            list = list.stream().map(i -> {
                try {
                    i.setCreator(studentDao.findById(i.getSociety_creator()));
                } catch (SQLException e) {
                    request.setAttribute("exception", e);
                }
                return i;
            }).collect(Collectors.toList());
        } catch (SQLException e) {
            handleException(e, request, response);
            return "error"; // Ensure to return an error page in case of failure
        }
        request.setAttribute("societies", list);
        return "mainIndex";
    }

    public String addSociety(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String intro = req.getParameter("intro");
        System.out.println("Received name: " + name);
        System.out.println("Received intro: " + intro);


        Society society = new Society();
        society.setSociety_intro(intro);
        society.setSociety_name(name);
        society.setSociety_status(1);

        String number = (String) req.getSession().getAttribute("student");
        try {
            society.setSociety_creator(studentDao.findByNumber(number).getStudentId());
        } catch (SQLException e) {
            handleException(e, req, res);
            return "error";
        }

        society.setSociety_create_date(new Date());

        try {
            societyDao.insertSociety(society);
            res.sendRedirect("/studentSociety/society/mainIndex");
            return "direct"; // Optional, since sendRedirect doesn't return control
        } catch (SQLException e) {
            handleException(e, req, res);
            return "error";
        }
    }

    public Map<String, Object> updateSociety(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String intro = req.getParameter("intro");

        Map<String, Object> responseMap = new HashMap<>();
        Integer id = idStr != null ? Integer.valueOf(idStr) : null;

        if (id == null) {
            responseMap.put("message", "Invalid ID");
            return responseMap;
        }

        Society society = new Society();
        society.setSocietyId(id);
        society.setSociety_name(name);
        society.setSociety_intro(intro);

        try {
            int result = societyDao.updateSociety(society);
            responseMap.put("message", result);
        } catch (SQLException e) {
            responseMap.put("message", e.getMessage());
        }
        return responseMap;
    }

    public Map<String, Object> changeStatus(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String statusStr = req.getParameter("status");

        Map<String, Object> responseMap = new HashMap<>();

        try {
            Integer id = Integer.valueOf(idStr);
            Integer status = Integer.parseInt(statusStr);
            Society society = new Society();
            society.setSocietyId(id);
            society.setSociety_status(status);

            int result = societyDao.updateSociety(society);
            responseMap.put("result", result == 1 ? "Status updated" : "Failed to update status");
        } catch (SQLException e) {
            req.setAttribute("exception", e);
            responseMap.put("error", "Database error");
        } catch (NumberFormatException e) {
            responseMap.put("error", "Invalid number format");
        }

        return responseMap;
    }
}
