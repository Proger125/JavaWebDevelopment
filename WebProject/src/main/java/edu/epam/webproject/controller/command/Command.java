package edu.epam.webproject.controller.command;


import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface provides method to interact with requests
 */
public interface Command {
    Router execute(HttpServletRequest req);
}
