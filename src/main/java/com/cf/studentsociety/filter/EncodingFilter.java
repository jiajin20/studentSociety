package com.cf.studentsociety.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@WebFilter(filterName = "EncodingFilter",urlPatterns = "/*"
        ,initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class EncodingFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        HttpServletRequest reque = new HttpServletRequestWrapper(req) {
            @Override
            public Map<String, String[]> getParameterMap() {
                Map<String, String[]> map = super.getParameterMap();
                for (String key : map.keySet()) {
                    String[] arr = map.get(key);
                    for (int i = 0; i < arr.length; i++) {
                        try {
                            arr[i] = new String(arr[i].getBytes("ISO8859-1"), encoding);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return map;
            }

        };
        if ("get".equals(req.getMethod().toLowerCase())) {
            reque.getParameterMap();
            req = reque;
        }
        chain.doFilter(req, res);


    }
}