package edu.epam.webproject.controller.command;

import edu.epam.webproject.controller.command.impl.*;
import edu.epam.webproject.controller.command.impl.admin.func.ChangeUserStatusCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAllOffersPageCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAllReservationsPageCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAllUsersPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToAboutPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignInPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignUpPageCommand;
import edu.epam.webproject.controller.command.impl.user.ActivateAccountCommand;
import edu.epam.webproject.controller.command.impl.user.go.GoToUserAccountPageCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private final EnumMap<CommandType, Command> commands = new EnumMap<CommandType, Command>(CommandType.class);
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
        commands.put(CommandType.ACTIVATE_ACCOUNT_COMMAND, new ActivateAccountCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
    }

    public static CommandProvider getInstance(){
        return instance;
    }

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
