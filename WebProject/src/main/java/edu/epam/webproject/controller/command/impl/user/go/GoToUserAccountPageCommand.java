package edu.epam.webproject.controller.command.impl.user.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.Router;
import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.OfferService;
import edu.epam.webproject.model.service.ReservationService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToUserAccountPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        ServiceProvider provider = ServiceProvider.getInstance();
        OfferService offerService = provider.getOfferService();
        ReservationService reservationService = provider.getReservationService();
        User user = (User) req.getSession().getAttribute(RequestAttribute.USER);
        long id = user.getId();
        try{
            List<Offer> offers = offerService.findOffersByOwnerId(id);
            req.setAttribute(RequestAttribute.OFFERS_LIST, offers);
            List<Reservation> reservations = reservationService.findReservationsByTenantId(id);
            req.setAttribute(RequestAttribute.RESERVATIONS_LIST, reservations);
            router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Can't find all users");
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
