package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ReservationService;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class BookCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        int arrivalDateIndex = Integer.parseInt(req.getParameter(RequestParameter.ARRIVAL_DATE_INDEX));
        int departureDate = Integer.parseInt(req.getParameter(RequestParameter.DEPARTURE_DATE_INDEX));
        long offer_id = Long.parseLong(req.getParameter(RequestParameter.OFFER_ID).trim());
        User user = (User) req.getSession().getAttribute(RequestAttribute.USER);
        BigInteger pricePerDay = BigInteger.valueOf(Long.parseLong(req.getParameter(RequestParameter.PRICE_PER_DAY).trim()));
        List<Date> allDays = DateUtil.setUpDateList();

        ServiceProvider provider = ServiceProvider.getInstance();
        ReservationService service = provider.getReservationService();
        try{
            service.addNewReservation(user.getId(), offer_id, allDays.get(arrivalDateIndex), allDays.get(departureDate), pricePerDay);
            router = new Router(null, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Can't find all offers");
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
