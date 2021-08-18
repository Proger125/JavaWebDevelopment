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

public class ForgetPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        String email = req.getParameter(RequestParameter.EMAIL);
        try{
            boolean result = service.checkUserByEmail(email);
            if (result){
                MailSender.setForgetPasswordLetter(email);
                req.setAttribute(RequestAttribute.EMAIL, email);
                router = new Router(PagePath.FORGET_PASSWORD_PAGE, Router.RouterType.FORWARD);
            }else{
                req.setAttribute(RequestAttribute.INCORRECT_DATA, true);
                router = new Router(PagePath.FORGET_PASSWORD_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error at SignUp servlet", e);
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
