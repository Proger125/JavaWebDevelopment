package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        String login = req.getParameter(RequestParameter.LOGIN);
        String email = req.getParameter(RequestParameter.EMAIL);
        String password = req.getParameter(RequestParameter.PASSWORD);

        try{
            boolean result = userService.signUp(login, email, password);
            if (result){
                //TODO Send email
                router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
                        req.setAttribute(RequestAttribute.EMAIL_SENT, true);
            }else{
                router = new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
                req.setAttribute(RequestAttribute.DUPLICATE_EMAIL, true);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error at SignUp servlet", e);
            req.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
