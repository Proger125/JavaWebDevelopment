package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.Router;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUserIconCommand implements Command {
    private static final String UPLOAD_USER_ICON_DIR = "/static/img/users";
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        String applicationDir = req.getServletContext().getRealPath("");
        String randFileName = null;
        String uploadFileDir = applicationDir + File.separator + UPLOAD_USER_ICON_DIR + File.separator;

        try {
            for (Part part : req.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    String path = part.getSubmittedFileName();
                    randFileName = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
                    part.write(uploadFileDir + File.separator + randFileName);
                }
            }
            User user = (User) req.getSession().getAttribute(RequestAttribute.USER);
            String icon = UPLOAD_USER_ICON_DIR + File.separator + randFileName;
            user.setIcon(icon);
            req.getSession().setAttribute(RequestAttribute.USER, user);
            service.updateUserIconById(user.getId(), icon);
            if (user.getRole() == User.Role.ADMIN){
                router = new Router(PagePath.GO_TO_ADMIN_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
            }else{
                router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException | IOException | ServletException e) {
            logger.log(Level.ERROR, "Error at UploadUserIcon servlet", e);
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.DEFAULT_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
