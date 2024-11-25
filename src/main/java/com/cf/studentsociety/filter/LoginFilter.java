package com.cf.studentsociety.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = new HashSet<>(
            Arrays.asList(
                    "/studentSociety/index.jsp",
                    "/studentSociety/",
                    "/studentSociety/student/login"
            )
    );

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI(); // 获取访问路径
        HttpSession session = req.getSession(false); // 获取会话（不创建）

        // 如果是允许访问的路径，直接放行
        if (isAllowedPath(url)) {
            chain.doFilter(req, res);
            return;
        }

        // 检查登录状态
        String student = (session != null) ? (String) session.getAttribute("student") : null;
        if (student == null) {
            String redirectUrl = req.getContextPath() + "?message=" + URLEncoder.encode("请先登录", "UTF-8");
            res.sendRedirect(redirectUrl);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * 检查是否是允许直接访问的路径
     */
    private boolean isAllowedPath(String url) {
        // 直接匹配路径，支持精确匹配的免过滤路径
        return ALLOWED_PATHS.contains(url);
    }
}
