package edu.epam.webproject.controller.command;

/**
 * Class designed to store attributes that could be in the request
 */
public class RequestAttribute {
    public static final String EXCEPTION = "exception";
    public static final String DUPLICATE_EMAIL = "duplicate_email";
    public static final String EMAIL_CONFIRM = "email_confirm";
    public static final String USER = "user";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String INCORRECT_DATA = "incorrectData";
    public static final String DUPLICATE_ADDRESS = "duplicate_address";
    public static final String LOCALE = "locale";
    public static final String LAST_PAGE = "last_page";

    public static final String USERS_LIST = "users_list";
    public static final String OFFERS_LIST = "offers_list";
    public static final String RESERVATIONS_LIST = "reservations_list";

    public static final String OFFER_ID = "offer_id";
    public static final String OFFER = "offer";
    public static final String BOOKED_DAYS_LIST = "booked_days_list";
    public static final String ALL_DAYS_LIST = "all_days_list";

    public static final String IN_ACTIVE_RESERVATIONS_LIST = "in_active_reservations_list";
    public static final String BAN_MESSAGE = "ban_message";
    public static final String ROLE_MESSAGE = "role_message";
    private RequestAttribute(){}
}
