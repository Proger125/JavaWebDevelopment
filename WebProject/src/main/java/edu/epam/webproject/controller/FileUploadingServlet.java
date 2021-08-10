package edu.epam.webproject.controller;

import edu.epam.webproject.controller.command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
                maxFileSize = 1024 * 1024 * 5,
                maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        Command command = commandProvider.getCommand(commandName);
        Router router = command.execute(req);
        switch (router.getType()){
            case REDIRECT:
                resp.sendRedirect(router.getPagePath());
                break;
            case FORWARD:
                req.getRequestDispatcher(router.getPagePath()).forward(req, resp);
                break;
            default:
                logger.log(Level.ERROR, "Incorrect router type: " + router.getType());
                resp.sendError(404);
        }
        resp.sendRedirect(router.getPagePath());
    }
}
