package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        req.getSession(false).invalidate();
        Router router = new Router(PagePath.ABOUT_PAGE, Router.RouterType.REDIRECT);
        return router;
    }
}
