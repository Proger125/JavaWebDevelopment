package edu.epam.webproject.controller.command.impl.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public class GoToAboutPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router(PagePath.ABOUT_PAGE, Router.RouterType.REDIRECT);
        req.getSession().setAttribute(RequestAttribute.LOCALE, new Locale("ru"));
        return router;
    }
}
