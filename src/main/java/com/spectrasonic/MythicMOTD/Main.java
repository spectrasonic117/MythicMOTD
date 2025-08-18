package com.spectrasonic.MythicMOTD;

import com.spectrasonic.MythicMOTD.managers.CommandManager;
import com.spectrasonic.MythicMOTD.managers.ConfigManager;
import com.spectrasonic.MythicMOTD.managers.EventManager;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import lombok.Getter;

import com.spectrasonic.Utils.CommandUtils;
import com.spectrasonic.Utils.MessageUtils;

import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Main extends JavaPlugin {

    private ConfigManager configManager;
    private CommandManager commandManager;
    private EventManager eventManager;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        CommandAPI.onEnable();

        this.configManager = new ConfigManager(this);
        this.commandManager = new CommandManager(this);
        this.eventManager = new EventManager(this);
        
        CommandUtils.setPlugin(this);
        MessageUtils.sendStartupMessage(this);

        CommandUtils.setPlugin(this);
        MessageUtils.sendStartupMessage(this);

    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
        MessageUtils.sendShutdownMessage(this);
    }

}