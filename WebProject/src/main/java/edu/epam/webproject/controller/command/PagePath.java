package edu.epam.webproject.controller.command;

public class PagePath {
    public static final String ABOUT_PAGE = "pages/all/about.jsp";
    public static final String SIGN_UP_PAGE = "pages/all/sign_up.jsp";
    public static final String SIGN_IN_PAGE = "pages/all/sign_in.jsp";
    public static final String ADMIN_ACCOUNT_PAGE = "pages/admin/admin_account.jsp";
    public static final String USER_ACCOUNT_PAGE = "pages/user/user_account.jsp";
    public static final String DEFAULT_PAGE = "pages/all/default.jsp";

    public static final String ALL_USERS_PAGE = "pages/admin/all_users.jsp";
    public static final String ALL_OFFERS_PAGE = "pages/admin/all_offers.jsp";
    public static final String ALL_RESERVATIONS_PAGE = "pages/admin/all_reservations.jsp";

    public static final String ADD_NEW_OFFER_PAGE = "pages/user/add_new_offer.jsp";
    public static final String ADD_PHOTOS_PAGE = "pages/user/add_photos.jsp";
    public static final String OFFER_PAGE = "pages/user/offer.jsp";

    public static final String GO_TO_ALL_USERS_PAGE = "Controller?command=go_to_all_users_page_command";
    public static final String GO_TO_ALL_OFFERS_PAGE = "Controller?command=go_to_all_offers_page_command";
    public static final String GO_TO_ALL_RESERVATIONS_PAGE = "Controller?command=go_to_all_reservations_page_command";

    public static final String GO_TO_USER_ACCOUNT_PAGE = "Controller?command=go_to_user_account_page_command";
    public static final String GO_TO_OFFER_PAGE = "Controller?command=go_to_offer_page_command";

    public static final String GO_TO_ADMIN_ACCOUNT_PAGE = "Controller?command=go_to_admin_account_page_command";
    private PagePath(){}
}
