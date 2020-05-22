/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.text.MessageFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import com.*;

/**
 *
 * @author user
 */
public class CreateCommand {
    private final Properties properties;
    private CreateCommand createCommand;
    private final String baseName = "Messages";
    private Locale locale;
    private ResourceBundle resourceBundle;
    public CreateCommand() {
        this.properties = new Properties();
        String fileName = "Commands.properties";
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyName(String key) {
        return this.properties.getProperty(key);
    }
    
    private Command CreateCommand(String commandName, String... arguments) {
        Command commandInstance = null;
        try {
            Class<?> commandClass = Class.forName(createCommand.getPropertyName(commandName + ".impl"));

            Class<?>[] signature = (arguments.length == 0 ? new Class[]{String.class} : new Class[]{String.class, String.class});

            Constructor<?> constructor = commandClass.getConstructor(signature);

            if (arguments.length == 0) {
                commandInstance = (Command) constructor.newInstance(createCommand.getPropertyName(commandName + ".command"));
            } else {
                commandInstance = (Command) constructor.newInstance(createCommand.getPropertyName(commandName + ".command"), arguments[0]);
            }
        } catch (InstantiationException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return commandInstance;
    }
    
    public String getMessage(String key, String... arguments) {
        String pattern = resourceBundle.getString(key);
        return new MessageFormat(pattern).format(arguments);
    }

    /**
     *
     * @return
     */
    public Map<String, Object> readCommand() {
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();

        String[] commandArguments = request.split(" ", 2);
        Command command = shell.getCommand(commandArguments[0]);

        Map<String, Object> commandMap = new HashMap<>();
        commandMap.put("command", command);
        commandMap.put("arguments", commandArguments);
        return commandMap;
    }

    /**
     *
     * @param commandMap
     * @return
     */
    public String executeCommand(Map<String, Object> commandMap) {
        Command command = (Command) commandMap.get("command");
        if (command == null) {
            return getMessage("invalid");
        } else if (command.getCommand().equals("show-cmds")) {
            return command.execute(this.shell);
        } else if (command.getCommand().equals(createCommand.getPropertyName("display-locales.command"))) {
            return command.execute(this);
        } else if (command.getCommand().equals(createCommand.getPropertyName("set-locale.command"))) {
            String tag = ((String[]) commandMap.get("arguments"))[1];
            return command.execute(this, tag);
        } else if (command.getCommand().equals(createCommand.getPropertyName("info.command"))) {
            String[] arguments = ((String[]) commandMap.get("arguments"));
            if (arguments.length > 1) {
                return command.execute(this, arguments[1]);
            }
            return command.execute(this);
        }

        return command.execute();
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(String tag) {
        this.locale = (Locale.forLanguageTag(tag));
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
    }
    
}
 

