package edu.epam.webproject.controller.filter;

import edu.epam.webproject.controller.command.RequestAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;
@WebFilter(urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Locale locale = (Locale) session.getAttribute(RequestAttribute.LOCALE);

        if (locale == null){
            session.setAttribute(RequestAttribute.LOCALE, new Locale("en"));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
