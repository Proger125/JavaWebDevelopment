package edu.epam.webproject.controller.command;

import edu.epam.webproject.controller.command.impl.*;
import edu.epam.webproject.controller.command.impl.go.GoToAboutPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignInPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignUpPageCommand;

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
        commands.put(CommandType.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
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
