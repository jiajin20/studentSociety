//package com.cf.studentsociety.web;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//public abstract class RouteServlet extends HttpServlet
//{
//    private String getRoute(String url){
//        url = url.replaceAll("/studentSociety","");
//        String[] levels = url.split("[?]")[0].split("/");
//        return levels[levels.length-1];
//    }
//    protected abstract Class getMyClass();
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json;charset=UTF-8");
//
//        String url = req.getRequestURI();
//        String methodName = getRoute(url);
//        Object obj = null;
//        try {
//            Method method = getMyClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
//            obj = method.invoke(this,req,resp);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//            handleException(e,req,resp);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            handleException(e,req,resp);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//            handleException(e,req,resp);
//        }
//        forward(obj,req,resp);
//    }
//    private String getJson(Object obj) throws IOException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setSerializationInclusion (JsonInclude.Include.NON_NULL); // 允许空属性
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    private void forward(Object obj,HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
//        if( obj != null){
//            if(obj instanceof String){
//                if(!"direct".equals((String)obj)){
//                    req.getRequestDispatcher("/WEB-INF/jsp/"+ (String)obj+".jsp").forward(req,resp);
//                }
//            }else{
//                // 转化为json
//                String json = getJson(obj);
//                PrintWriter out = resp.getWriter();
//                out.write(json);
//                out.flush();
//                out.close();
//            }
//        }else{
//            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req,resp);
//        }
//    }
//    protected void handleException(Throwable e,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
//        req.setAttribute("exception",e);
//        req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req,resp);
//    }
//
//
//}
//
//

package com.cf.studentsociety.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class RouteServlet extends HttpServlet {

    private String getRoute(String url) {
        url = url.replaceAll("/studentSociety", "");
        String[] levels = url.split("[?]")[0].split("/");
        return levels[levels.length - 1];
    }

    protected abstract Class<?> getMyClass();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        String url = req.getRequestURI();
        String methodName = getRoute(url);
        Object obj = null;

        try {
            Method method = getMyClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            obj = method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            handleException(e, req, resp);
            return; // 确保异常处理后不再执行后续逻辑
        }

        forward(obj, req, resp);
    }

    private String getJson(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 忽略空属性
        return objectMapper.writeValueAsString(obj);
    }

    private void forward(Object obj, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (obj != null) {
            if (obj instanceof String) {
                // 确保响应未提交才转发到 JSP
                if (!resp.isCommitted()) {
                    String page = (String) obj;
                    if (!"direct".equals(page)) {
                        req.getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp").forward(req, resp);
                    }
                }
            } else {
                // 将返回的对象转换为 JSON 并写入响应
                String json = getJson(obj);
                if (!resp.isCommitted()) {
                    PrintWriter out = resp.getWriter();
                    out.write(json);
                    out.flush(); // 确保数据写入完成
                }
            }
        } else {
            // 如果对象为 null，则转发到错误页面
            if (!resp.isCommitted()) {
                req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
            }
        }
    }

    protected void handleException(Throwable e, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("exception", e);
        // 只在响应未提交的情况下转发到错误页面
        if (!resp.isCommitted()) {
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}
