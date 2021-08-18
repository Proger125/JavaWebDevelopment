package edu.epam.webproject.controller.filter;

import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(urlPatterns = {"/*"})
public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        User.Role role = (User.Role) session.getAttribute(RequestAttribute.ROLE);

        if (role == null){
            session.setAttribute(RequestAttribute.ROLE, User.Role.GUEST);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
