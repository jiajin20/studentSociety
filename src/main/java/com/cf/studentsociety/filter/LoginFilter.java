package com.cf.studentsociety.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        if("/studentSociety/index.jsp".equals(url) ||
                "/studentSociety/".equals(url) ||
                "/studentSociety/student/login".equals(url)){
            chain.doFilter(req,res);
        }else{
            HttpSession session = req.getSession();
            String student = (String)(session.getAttribute("student"));
            if(student == null){
                res.sendRedirect("studentSociety?message"+ URLEncoder.encode("请先登录","UTF-8"));
            }else{
                chain.doFilter(req,res);
            }
        }



    }
}
