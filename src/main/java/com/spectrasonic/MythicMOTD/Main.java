package com.spectrasonic.MythicMOTD;

import com.spectrasonic.MythicMOTD.managers.CommandManager;
import com.spectrasonic.MythicMOTD.managers.ConfigManager;
import com.spectrasonic.MythicMOTD.managers.EventManager;
import com.spectrasonic.MythicMOTD.utils.MessageUtils;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Main extends JavaPlugin {

    private ConfigManager configManager;
    private CommandManager commandManager;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.configManager = new ConfigManager(this);
        this.commandManager = new CommandManager(this);
        this.eventManager = new EventManager(this);

        MessageUtils.sendStartupMessage(this);

    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

}