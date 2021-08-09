package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import edu.epam.webproject.util.MailSender;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.epam.webproject.model.service.UserService.INCORRECT_DATA_EXCEPTION_MESSAGE;

public class SignUpCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    //public static final String INCORRECT_DATA_EXCEPTION_MESSAGE = "Incorrect user data";

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
                MailSender.send(email);
                req.setAttribute(RequestAttribute.EMAIL, email);
                router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.FORWARD);
                req.getSession().setAttribute(RequestAttribute.EMAIL_CONFIRM, false);
            }else{
                router = new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
                req.getSession().setAttribute(RequestAttribute.DUPLICATE_EMAIL, true);
            }

        } catch (ServiceException e) {
            if (e.getMessage().equals(INCORRECT_DATA_EXCEPTION_MESSAGE)){
                req.getSession().setAttribute(RequestAttribute.INCORRECT_DATA, true);
                router = new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
            }else{
                logger.log(Level.ERROR, "Error at SignUp servlet", e);
                req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
                router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
            }
        }
        return router;
    }
}
