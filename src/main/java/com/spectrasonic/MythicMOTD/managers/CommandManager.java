package com.spectrasonic.MythicMOTD.managers;

import com.spectrasonic.MythicMOTD.Main;
import com.spectrasonic.MythicMOTD.commands.MOTDCommand;
import lombok.Getter;

@Getter
public class CommandManager {

    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
        registerCommands();
    }

    private void registerCommands() {
        new MOTDCommand(plugin);
    }
}