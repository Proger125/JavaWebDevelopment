package edu.epam.webproject.controller.command.impl.admin.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.Router;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ReservationService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class GoToAllReservationsPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        ServiceProvider provider = ServiceProvider.getInstance();
        ReservationService service = provider.getReservationService();
        try{
            List<Reservation> reservations = service.findAllReservations();
            req.setAttribute(RequestAttribute.RESERVATIONS_LIST, reservations);
            router = new Router(PagePath.ALL_RESERVATIONS_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Can't find all reservations");
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
