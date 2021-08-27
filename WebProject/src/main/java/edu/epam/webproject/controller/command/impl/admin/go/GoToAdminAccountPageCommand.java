package edu.epam.webproject.controller.command.impl.admin.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAdminAccountPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router(PagePath.ADMIN_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
        return router;
    }
}
