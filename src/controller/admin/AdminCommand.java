package controller.admin;

import exceptions.IllegalCommandException;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AdminCommand {

    ADD_STUDENT("add student ([a-zA-Z0-9_ ]+) ([a-zA-Z0-9_ ]+) (\\w+) (\\d+) (yes|no)"),
    ADD_DISTRIBUTOR("add distributor ([a-zA-Z0-9_ ]+) ([a-zA-Z0-9_ ]+) (\\w+) ([a-zA-Z0-9_ ]+)"),
    ADD_ADMIN("add admin ([a-zA-Z0-9_ ]+) ([a-zA-Z0-9_ ]+) (\\w+)"),
    ADD_FOOD("add food (\\w+) (\\d+) (dinner|breakfast|lunch)"),
    SET_FOOD("set food (\\w+) (dinner|breakfast|lunch)"),
    SHOW_DEMANDS("show demands (\\d+)");

    private final Pattern pattern;
    private final String regex;
    AdminCommand(String regex) {
        pattern = Pattern.compile(regex);
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, AdminCommand command) {
        return command.pattern.matcher(input.toLowerCase(Locale.ROOT));
    }

    public static AdminCommand findCommand(String input) {
        for (AdminCommand command : AdminCommand.values()) {
            if(Pattern.matches(command.regex, input))
                return command;
        }
        throw new IllegalCommandException();
    }
}
