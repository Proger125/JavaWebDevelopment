package edu.epam.webproject.controller.command;

import edu.epam.webproject.controller.command.impl.*;
import edu.epam.webproject.controller.command.impl.admin.func.ChangeUserStatusCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAdminAccountPageCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAllOffersPageCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAllReservationsPageCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAllUsersPageCommand;
import edu.epam.webproject.controller.command.impl.go.*;
import edu.epam.webproject.controller.command.impl.user.func.*;
import edu.epam.webproject.controller.command.impl.user.go.*;

import java.util.EnumMap;

/**
 * Class provides methods to interact with commands
 */
public class CommandProvider {
    /**
     * Instance of singleton
     */
    private static final CommandProvider instance = new CommandProvider();

    /**
     * Enum map that contains type of command as a key and command as a value
     */
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    /**
     * Private constructor that initialize enum map
     */
    private CommandProvider(){
        commands.put(CommandType.SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.LOG_OUT_COMMAND, new LogOutCommand());
        commands.put(CommandType.GO_TO_ABOUT_PAGE_COMMAND, new GoToAboutPageCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
        commands.put(CommandType.GO_TO_SIGN_IN_PAGE_COMMAND, new GoToSignInPageCommand());
        commands.put(CommandType.GO_TO_ALL_OFFERS_PAGE_COMMAND, new GoToAllOffersPageCommand());
        commands.put(CommandType.GO_TO_ALL_RESERVATIONS_PAGE_COMMAND, new GoToAllReservationsPageCommand());
        commands.put(CommandType.GO_TO_ALL_USERS_PAGE_COMMAND, new GoToAllUsersPageCommand());
        commands.put(CommandType.GO_TO_USER_ACCOUNT_PAGE_COMMAND, new GoToUserAccountPageCommand());
        commands.put(CommandType.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(CommandType.CHANGE_USER_STATUS_COMMAND, new ChangeUserStatusCommand());
        commands.put(CommandType.CHANGE_OFFER_STATUS_COMMAND, new ChangeOfferStatusCommand());
        commands.put(CommandType.ACTIVATE_ACCOUNT_COMMAND, new ActivateAccountCommand());
        commands.put(CommandType.UPLOAD_USER_ICON_COMMAND, new UploadUserIconCommand());
        commands.put(CommandType.GO_TO_ADD_NEW_OFFER_PAGE_COMMAND, new GoToAddNewOfferPageCommand());
        commands.put(CommandType.GO_TO_ALL_OFFERS_PAGE_USER_COMMAND, new GoToAllOffersPageUserCommand());
        commands.put(CommandType.GO_TO_OFFER_PAGE_COMMAND, new GoToOfferPageCommand());
        commands.put(CommandType.ADD_NEW_OFFER_COMMAND, new AddNewOfferCommand());
        commands.put(CommandType.ADD_PHOTOS_TO_OFFER_BY_ID, new AddPhotosToOfferById());
        commands.put(CommandType.CHANGE_RESERVATION_STATUS_COMMAND, new ChangeReservationStatusCommand());
        commands.put(CommandType.BOOK_COMMAND, new BookCommand());
        commands.put(CommandType.GO_TO_ADMIN_ACCOUNT_PAGE_COMMAND, new GoToAdminAccountPageCommand());
        commands.put(CommandType.GO_TO_ADD_PHOTOS_TO_OFFER_BY_ID, new GoToAddPhotosToOfferById());
        commands.put(CommandType.GO_TO_FORGET_PASSWORD_PAGE, new GoToForgetPasswordPage());
        commands.put(CommandType.FORGET_PASSWORD_COMMAND, new ForgetPasswordCommand());
        commands.put(CommandType.GO_TO_CHANGE_USER_PASSWORD_PAGE_COMMAND, new GoToChangeUserPasswordPageCommand());
        commands.put(CommandType.CHANGE_USER_PASSWORD_COMMAND, new ChangeUserPasswordCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
    }

    /**
     * Returns instance of singleton
     * @return {@link CommandProvider}
     */
    public static CommandProvider getInstance(){
        return instance;
    }

    /**
     * Return {@link Command} by its string representation
     * @param command - string representation of command
     * @return {@link Command}
     */
    public Command getCommand(String command){
        if (command == null){
            return commands.get(CommandType.DEFAULT);
        }
        CommandType type;
        try{
            type = CommandType.valueOf(command.toUpperCase());
        }catch (IllegalArgumentException e){
            type = CommandType.DEFAULT;
        }
        return commands.get(type);
    }
}
