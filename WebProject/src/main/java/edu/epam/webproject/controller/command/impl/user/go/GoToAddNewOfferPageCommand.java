package edu.epam.webproject.controller.command.impl.user.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAddNewOfferPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router(PagePath.ADD_NEW_OFFER_PAGE, Router.RouterType.REDIRECT);
        return router;
    }
}
