package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.OfferService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddPhotosToOfferById implements Command {
    private static final String UPLOAD_OFFER_PHOTOS_DIR = "/static/img/offers";
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        long offer_id = Long.parseLong(req.getParameter(RequestParameter.OFFER_ID));

        ServiceProvider provider = ServiceProvider.getInstance();
        OfferService service = provider.getOfferService();

        String applicationDir = req.getServletContext().getRealPath("");
        String randFileName = null;
        String uploadFileDir = applicationDir + File.separator + UPLOAD_OFFER_PHOTOS_DIR + File.separator;
        String oldPathName = null;
        List<String> photos = new ArrayList<>();
        try{
            for (Part part : req.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    String path = part.getSubmittedFileName();
                    if (!path.equals(oldPathName)){
                        oldPathName = path;
                        randFileName = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
                        photos.add(UPLOAD_OFFER_PHOTOS_DIR + File.separator + randFileName);
                    }
                    part.write(uploadFileDir + File.separator + randFileName);
                }
            }
            service.addPhotosToOfferById(offer_id, photos);
            router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
        } catch (ServletException | IOException | ServiceException e) {
            logger.log(Level.ERROR, "Error at AddPhotosToOfferById servlet", e);
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
