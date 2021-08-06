package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static edu.epam.webproject.model.service.UserService.INCORRECT_DATA_EXCEPTION_MESSAGE;

public class SignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        String email = req.getParameter(RequestParameter.EMAIL);
        String password = req.getParameter(RequestParameter.PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        try{
            Optional<User> optionalUser = userService.signIn(email, password);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                req.getSession().setAttribute(RequestAttribute.USER, user);
                if (user.getRole() == User.Role.ADMIN){
                    router = new Router(PagePath.ADMIN_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
                }else {
                    router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
                }
            }else{
                req.getSession().setAttribute(RequestAttribute.INCORRECT_DATA, true);
                router = new Router(PagePath.SIGN_IN_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
