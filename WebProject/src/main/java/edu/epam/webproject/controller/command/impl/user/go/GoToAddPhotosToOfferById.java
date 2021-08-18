package edu.epam.webproject.controller.command.impl.user.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAddPhotosToOfferById implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        long id = (Long) req.getSession().getAttribute(RequestAttribute.OFFER_ID);
        req.setAttribute(RequestAttribute.OFFER_ID, id);
        router = new Router(PagePath.ADD_PHOTOS_PAGE, Router.RouterType.FORWARD);
        return router;
    }
}
