package edu.epam.webproject.controller.command;

public class Router {
    public enum RouterType{
        FORWARD, REDIRECT
    }

    private final String pagePath;
    private final RouterType type;

    public Router(String pagePath, RouterType type){
        this.pagePath = pagePath;
        this.type = type;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getType() {
        return type;
    }
}
