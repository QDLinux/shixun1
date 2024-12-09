package org.example.filter;

import org.example.entity.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.action"})
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String requestURI = req.getRequestURI();
        System.out.println(requestURI);

        HttpSession session = req.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            resp.setStatus(401);
            //resp.sendRedirect("/login.html?s=1");
        }
        else {
            filterChain.doFilter(req, resp);//放行
        }
    }
}
