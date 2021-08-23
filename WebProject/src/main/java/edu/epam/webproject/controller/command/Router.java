package edu.epam.webproject.controller.command;

/**
 * Class designed to store information about "where to go" and "how to go" after command execution
 */
public class Router {
    public enum RouterType{
        FORWARD, REDIRECT
    }

    /**
     * Path on which the request will go
     */
    private final String pagePath;

    /**
     * Request forwarding type
     */
    private final RouterType type;

    /**
     * Constructor with specified parameters
     * @param pagePath - relative path of jsp
     * @param type - type of forwarding
     */
    public Router(String pagePath, RouterType type){
        this.pagePath = pagePath;
        this.type = type;
    }

    /**
     * Page path getter
     * @return page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Router type getter
     * @return router type
     */
    public RouterType getType() {
        return type;
    }
}
