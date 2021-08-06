package edu.epam.webproject.controller.command.impl.admin.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.Router;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToAllUsersPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();
        try{
            List<User> users = service.findAllUsers();
            req.setAttribute(RequestAttribute.USERS_LIST, users);
            router = new Router(PagePath.ALL_USERS_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Can't find all users");
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
