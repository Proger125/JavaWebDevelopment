package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActivateAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();
        String email = req.getParameter(RequestParameter.EMAIL);
        try{
            User user = service.activateUserByEmail(email);
            req.getSession().setAttribute(RequestAttribute.USER, user);
            req.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
            req.getSession().setAttribute(RequestAttribute.EMAIL_CONFIRM, true);
            router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error at SignUp servlet", e);
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
