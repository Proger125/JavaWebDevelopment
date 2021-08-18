package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.OfferService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;

public class AddNewOfferCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        String country = req.getParameter(RequestParameter.COUNTRY);
        String city = req.getParameter(RequestParameter.CITY);
        String street = req.getParameter(RequestParameter.STREET);
        int houseNumber = Integer.parseInt(req.getParameter(RequestParameter.HOUSE_NUMBER));
        int apartmentNumber = Integer.parseInt(req.getParameter(RequestParameter.APARTMENT_NUMBER));
        BigInteger pricePerDay = BigInteger.valueOf(Long.parseLong(req.getParameter(RequestParameter.PRICE_PER_DAY)));
        String description = req.getParameter(RequestParameter.DESCRIPTION);
        User user = (User)req.getSession().getAttribute(RequestAttribute.USER);
        long owner_id = user.getId();
        ServiceProvider provider = ServiceProvider.getInstance();
        OfferService offerService = provider.getOfferService();
        try {
            long result = offerService.addNewOffer(owner_id, pricePerDay, description, country, city, street, houseNumber, apartmentNumber);
            if (result != 0){
                req.getSession().setAttribute(RequestAttribute.OFFER_ID, result);
                req.setAttribute(RequestAttribute.OFFER_ID, result);
                router = new Router(PagePath.ADD_PHOTOS_PAGE, Router.RouterType.FORWARD);
            }else{
                req.getSession().setAttribute(RequestAttribute.DUPLICATE_ADDRESS, true);
                router = new Router(PagePath.ADD_NEW_OFFER_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error at UploadUserIcon servlet", e);
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
