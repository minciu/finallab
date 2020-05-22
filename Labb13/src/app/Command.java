package app;


public abstract class Command {
    private final String command;
    private String arguments;

    public Command(String command) {
        this.command = command;
    }

    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public abstract String execute(Object... arguments);

    @Override
    public String toString() {
        return getCommand() + " " + getArguments();
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return the arguments
     */
    public String getArguments() {
        return arguments;
    }
}