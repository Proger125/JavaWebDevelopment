package edu.epam.webproject.controller;

import edu.epam.webproject.controller.command.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
@WebServlet
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    public Controller(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        Command command = commandProvider.getCommand(commandName);
        Router router = command.execute(req);
        switch (router.getType()){
            case FORWARD:
                RequestDispatcher dispatcher = req.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(req, resp);
                break;
            case REDIRECT:
                resp.sendRedirect(router.getPagePath());
                break;
            default:
                logger.log(Level.ERROR, "Incorrect router type: " + router.getType());
                resp.sendError(404);
        }
    }

}
