package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ReservationService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeReservationStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;

        long reservation_id = Long.parseLong(req.getParameter(RequestParameter.RESERVATION_ID));
        long offer_id = Long.parseLong(req.getParameter(RequestParameter.OFFER_ID));
        Reservation.ReservationStatus status = Reservation.ReservationStatus.valueOf(req.getParameter(RequestParameter.STATUS));

        ServiceProvider provider = ServiceProvider.getInstance();
        ReservationService service = provider.getReservationService();
        try{
            service.changeReservationStatusById(reservation_id, status);
            req.setAttribute(RequestAttribute.OFFER_ID, offer_id);
            router = new Router(PagePath.GO_TO_OFFER_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error at ChangeUserStatus Servlet");
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
