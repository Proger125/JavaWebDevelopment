package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.RequestParameter;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        String locale = req.getParameter(RequestParameter.LOCALE);
        Locale loc = new Locale(locale);
        req.getSession().setAttribute(RequestAttribute.LOCALE, loc);
        String prevPage = (String) req.getSession().getAttribute(RequestAttribute.LAST_PAGE);
        System.out.println(prevPage);
        Router router = new Router(prevPage, Router.RouterType.REDIRECT);
        return router;
    }
}
