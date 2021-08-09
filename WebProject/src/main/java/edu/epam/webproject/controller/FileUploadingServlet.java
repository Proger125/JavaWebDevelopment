package edu.epam.webproject.controller;

import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.RequestAttribute;
import edu.epam.webproject.controller.command.RequestParameter;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
                maxFileSize = 1024 * 1024 * 5,
                maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final String UPLOAD_USER_ICON_DIR = "static/img/users";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applicationDir = req.getServletContext().getRealPath("");
        String req_type = req.getParameter(RequestParameter.REQ_UPLOAD_TYPE);
        try{
            String randFileName = null;
            String uploadFileDir = applicationDir + File.separator + UPLOAD_USER_ICON_DIR + File.separator;

            for (Part part : req.getParts()){
                if (part.getSubmittedFileName() != null){
                    String path = part.getSubmittedFileName();
                    randFileName = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
                    part.write(uploadFileDir + File.separator + randFileName);
                }
            }
            switch(req_type){
                case ReqUploadType.USER:
                    User user = (User) req.getSession().getAttribute(RequestAttribute.USER);
                    String icon = UPLOAD_USER_ICON_DIR + File.separator + randFileName;
                    user.setIcon(icon);
                    req.getSession().setAttribute(RequestAttribute.USER, user);
                    processUserRequest(user, icon);
                    resp.sendRedirect(PagePath.GO_TO_USER_ACCOUNT_PAGE);
                    break;
                case ReqUploadType.OFFER:
                    break;
            }
        }catch (ServiceException e){
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            resp.sendError(404);
        }
    }
    private static void processUserRequest(User user, String icon) throws ServiceException {
        UserService service = provider.getUserService();
        service.updateUserIconById(user.getId(), icon);
    }
    private static void processOfferRequest(long id){

    }
    private static class ReqUploadType{
        public static final String USER = "user";
        public static final String OFFER = "offer";
    }
}
