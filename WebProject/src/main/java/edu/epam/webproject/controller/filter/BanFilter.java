package edu.epam.webproject.controller.filter;

import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
//@WebFilter(urlPatterns = {"/Controller"})
public class BanFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        User user = (User) session.getAttribute(RequestAttribute.USER);
        try {
            if (user != null && service.findUserStatusById(user.getId()) == User.UserStatus.REJECTED){
                session.removeAttribute(RequestAttribute.USER);
                session.setAttribute(RequestAttribute.ROLE, User.Role.GUEST);
                httpServletRequest.setAttribute(RequestAttribute.BAN_MESSAGE, true);
                httpServletRequest.getRequestDispatcher(PagePath.ABOUT_PAGE).forward(httpServletRequest, httpServletResponse);
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "BanFilter error: " + e.getMessage());
            throw new ServletException("BanFilter error: " + e.getMessage(), e);
        }
    }
}
