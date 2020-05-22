/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import app.Command;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public class Shell {
    private List<Command> commands = new ArrayList<>();

    public Command getCommand(String commandName) {
        for (Command command : commands) {
            if (command.getCommand().equals(commandName)) {
                return command;
            }
        }
        return null;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : commands) {
            stringBuilder.append(command.getCommand());
            if (command.getArguments() != null) {
                stringBuilder.append(" ").append(command.getArguments());
            }
            stringBuilder.append("/");
        }

        return stringBuilder.toString();
    }
}
