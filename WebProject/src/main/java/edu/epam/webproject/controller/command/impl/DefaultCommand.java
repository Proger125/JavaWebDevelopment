package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        return null;
    }
}
