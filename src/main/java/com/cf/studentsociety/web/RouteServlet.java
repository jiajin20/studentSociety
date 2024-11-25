//package com.cf.studentsociety.web;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
//public abstract class RouteServlet extends HttpServlet {
//
//    private static final Logger logger = LoggerFactory.getLogger(RouteServlet.class);
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    static {
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 忽略空属性
//    }
//
//    /**
//     * 从请求的 URL 中提取方法名
//     */
//    private String getRoute(String url) {
//        url = url.replaceAll("/studentSociety", "");
//        String[] levels = url.split("[?]")[0].split("/");
//        if (levels.length == 0) {
//            return ""; // 返回空字符串或默认值
//        }
//        return levels[levels.length - 1];
//    }
//
//    /**
//     * 由子类实现，返回当前类
//     */
//    protected abstract Class<?> getMyClass();
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json;charset=UTF-8");
//
//        String url = req.getRequestURI();
//        String methodName = getRoute(url);
//        Object obj = null;
//
//        try {
//            Method method = getMyClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//            obj = method.invoke(this, req, resp);
//        } catch (NoSuchMethodException e) {
//            logger.error("No such method: " + methodName + " in class " + getMyClass().getName(), e);
//            handleException(e, req, resp);
//            return; // 确保异常处理后不再执行后续逻辑
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            logger.error("Error invoking method: " + methodName, e);
//            handleException(e, req, resp);
//            return;
//        }
//
//        forward(obj, req, resp);
//    }
//
//    /**
//     * 将对象转换为 JSON 字符串
//     */
//    private String getJson(Object obj) throws IOException {
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    /**
//     * 将响应结果转发到相应的页面或返回 JSON 响应
//     */
//    private void forward(Object obj, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (obj != null) {
//            if (obj instanceof String) {
//                // 确保响应未提交才转发到 JSP
//                if (!resp.isCommitted()) {
//                    String page = (String) obj;
//                    if (!"direct".equals(page)) {
//                        req.getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp").forward(req, resp);
//                    }
//                }
//            } else {
//                // 将返回的对象转换为 JSON 并写入响应
//                String json = getJson(obj);
//                if (!resp.isCommitted()) {
//                    PrintWriter out = resp.getWriter();
//                    out.write(json);
//                    out.flush(); // 确保数据写入完成
//                }
//            }
//        } else {
//            // 如果对象为 null，则转发到错误页面
//            if (!resp.isCommitted()) {
//                req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
//            }
//        }
//    }
//
//    /**
//     * 处理异常
//     */
//    protected void handleException(Throwable e, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 根据环境判断是否显示详细的堆栈信息
//        if (isDevelopmentEnvironment()) {
//            req.setAttribute("stackTrace", getStackTrace(e));
//        }
//        req.setAttribute("exception", e);
//        // 只在响应未提交的情况下转发到错误页面
//        if (!resp.isCommitted()) {
//            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
//        }
//    }
//
//    /**
//     * 判断是否为开发环境
//     */
//    private boolean isDevelopmentEnvironment() {
//        return "development".equalsIgnoreCase(System.getProperty("env"));
//    }
//
//    /**
//     * 获取异常的堆栈信息
//     */
//    private String getStackTrace(Throwable e) {
//        StringBuilder stackTrace = new StringBuilder();
//        for (StackTraceElement element : e.getStackTrace()) {
//            stackTrace.append(element.toString()).append("\n");
//        }
//        return stackTrace.toString();
//    }
//}
package com.cf.studentsociety.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    protected abstract Class getMyClass();

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
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Internal server error.");
            e.printStackTrace();

        }
        forward(obj, req, resp);
    }
    private String getJson(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 允许空属性
        return objectMapper.writeValueAsString(obj);
    }

    private void forward(Object obj, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (obj != null) {
            if (obj instanceof String) {
                if (!"direct".equals((String) obj)) {
                    req.getRequestDispatcher("/WEB-INF/jsp/" + (String) obj + ".jsp").forward(req, resp);
                }
            } else {
                // 转化为 JSON
                String json = getJson(obj);
                PrintWriter out = resp.getWriter();
                out.write(json);
                out.flush();
                // 不关闭 PrintWriter，以避免提交响应
            }
        } else {
            // 只在响应未提交的情况下转发
            if (!resp.isCommitted()) {
                req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
            }
        }
    }

    protected void handleException(Throwable e, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("exception", e);
        // 只在响应未提交的情况下转发
        if (!resp.isCommitted()) {
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}