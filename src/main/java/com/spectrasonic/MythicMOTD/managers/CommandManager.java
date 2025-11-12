package com.spectrasonic.MythicMOTD.managers;

import com.spectrasonic.MythicMOTD.Main;
import com.spectrasonic.MythicMOTD.commands.MOTDCommand;
import lombok.Getter;

import org.bukkit.command.PluginCommand;

@Getter
public class CommandManager {

    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
        registerCommands();
    }

    private void registerCommands() {
        MOTDCommand motdCommand = new MOTDCommand(plugin);
        
        PluginCommand mythicMotdCommand = getCommand("mythicmotd");
        if (mythicMotdCommand != null) {
            mythicMotdCommand.setExecutor(motdCommand);
            mythicMotdCommand.setTabCompleter(motdCommand);
        }
    }

    private PluginCommand getCommand(String name) {
        return plugin.getCommand(name);
    }
}