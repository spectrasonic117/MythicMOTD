package com.spectrasonic.MythicMOTD.managers;

import com.spectrasonic.MythicMOTD.Main;
import com.spectrasonic.MythicMOTD.listeners.MOTDListener;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class EventManager {

    private final Main plugin;

    public EventManager(Main plugin) {
        this.plugin = plugin;
        registerEvents();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new MOTDListener(plugin), plugin);
    }

}