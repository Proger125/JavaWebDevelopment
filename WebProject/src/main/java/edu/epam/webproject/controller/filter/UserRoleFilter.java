package edu.epam.webproject.controller.filter;

import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(urlPatterns = {"/pages/user/*"})
public class UserRoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        User.Role role = (User.Role) session.getAttribute(RequestAttribute.ROLE);
        if (role != User.Role.USER){
            httpServletRequest.setAttribute(RequestAttribute.ROLE_MESSAGE, true);
            httpServletRequest.getRequestDispatcher(PagePath.DEFAULT_PAGE).forward(httpServletRequest, httpServletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
