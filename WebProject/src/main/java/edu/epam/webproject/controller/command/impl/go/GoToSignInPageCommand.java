package edu.epam.webproject.controller.command.impl.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToSignInPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router(PagePath.SIGN_IN_PAGE, Router.RouterType.REDIRECT);
        return router;
    }
}
