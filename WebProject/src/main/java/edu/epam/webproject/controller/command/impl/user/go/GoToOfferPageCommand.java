package edu.epam.webproject.controller.command.impl.user.go;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.OfferService;
import edu.epam.webproject.model.service.ReservationService;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class GoToOfferPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;

        long id = Long.parseLong(req.getParameter(RequestParameter.OFFER_ID));

        ServiceProvider provider = ServiceProvider.getInstance();
        OfferService offerService = provider.getOfferService();
        ReservationService reservationService = provider.getReservationService();
        try{
            Offer offer = offerService.findOfferById(id);

            User user = (User)req.getSession().getAttribute(RequestAttribute.USER);
            if (offer.getOwner().getId() == user.getId()){
                List<Reservation> inActiveReservations = reservationService.findInActiveReservationsByOfferId(offer.getId());
                req.setAttribute(RequestAttribute.IN_ACTIVE_RESERVATIONS_LIST, inActiveReservations);
            }else{
                List<Date> bookedDays = reservationService.findAllBookedDaysByOfferId(id);
                List<Date> allDays = DateUtil.setUpDateList();
                req.setAttribute(RequestAttribute.ALL_DAYS_LIST, allDays);
                req.setAttribute(RequestAttribute.BOOKED_DAYS_LIST, bookedDays);
            }
            req.setAttribute(RequestAttribute.OFFER, offer);
            router = new Router(PagePath.OFFER_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Can't find all offers");
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
