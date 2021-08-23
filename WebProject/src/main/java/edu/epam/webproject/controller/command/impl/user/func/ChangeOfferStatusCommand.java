package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.OfferService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeOfferStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        String status = req.getParameter(RequestParameter.STATUS);
        long id = Long.parseLong(req.getParameter(RequestParameter.OFFER_ID));
        ServiceProvider provider = ServiceProvider.getInstance();
        OfferService offerService = provider.getOfferService();
        User.Role role = (User.Role) req.getSession().getAttribute(RequestAttribute.ROLE);
        try{
            offerService.changeOfferStatusById(id, Offer.OfferStatus.valueOf(status));

            if(role == User.Role.ADMIN){
                router = new Router(PagePath.GO_TO_ALL_OFFERS_PAGE, Router.RouterType.FORWARD);
            }else{
                router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error at ChangeOfferStatus Servlet");
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
