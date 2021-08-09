package edu.epam.webproject.controller.command.impl.admin.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ChangeUserStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        String status = req.getParameter(RequestParameter.STATUS);
        long id = Long.parseLong(req.getParameter(RequestParameter.USER_ID));
        String prevPage = (String) req.getSession().getAttribute(RequestAttribute.LAST_PAGE);
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            userService.changeUserStatusById(id, User.UserStatus.valueOf(status.toUpperCase()));
            router = new Router(PagePath.GO_TO_ALL_USERS_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
