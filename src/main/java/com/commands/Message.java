package com.commands;

public class Message {

    private String message;
    private String commandName;

    public Message(String message, String commandName) {
        this.message = message;
        this.commandName = commandName;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
