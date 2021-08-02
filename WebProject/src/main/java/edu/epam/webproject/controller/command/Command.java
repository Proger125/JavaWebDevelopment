package edu.epam.webproject.controller.command;


import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest req);
}
