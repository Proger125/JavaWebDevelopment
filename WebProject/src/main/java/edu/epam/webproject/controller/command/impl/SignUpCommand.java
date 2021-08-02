package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.RequestParameter;
import edu.epam.webproject.controller.command.Router;
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
                //TODO Go to profile page
                req.setAttribute(RequestAttribute.EMAIL_SENT, true);
            }else{
                //TODO Go to signUp page
                req.setAttribute(RequestAttribute.DUPLICATE_EMAIL, true);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error at SignUp servlet", e);
            req.setAttribute(RequestAttribute.EXCEPTION, e);
            //TODO G
        }
        return null;
    }
}
