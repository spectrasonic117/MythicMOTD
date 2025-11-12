package com.spectrasonic.MythicMOTD.commands;

import com.spectrasonic.MythicMOTD.Main;
import com.spectrasonic.MythicMOTD.utils.MessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MOTDCommand implements CommandExecutor, TabCompleter {

    private final Main plugin;
    private final List<String> subcommands = List.of("reload", "version", "help");

    public MOTDCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            String[] args) {
        if (args.length == 0) {
            return handleHelp(sender);
        }

        String subcommand = args[0].toLowerCase();

        switch (subcommand) {
            case "reload":
                return handleReload(sender);
            case "version":
                return handleVersion(sender);
            case "help":
            default:
                return handleHelp(sender);
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("mythicmotd.admin")) {
                for (String subcommand : subcommands) {
                    if (subcommand.startsWith(args[0].toLowerCase())) {
                        completions.add(subcommand);
                    }
                }
            }
        }

        return completions;
    }

    private boolean handleHelp(CommandSender sender) {
        if (!sender.hasPermission("mythicmotd.admin")) {
            MessageUtils.sendPermissionMessage(sender);
            return true;
        }

        MessageUtils.sendMessage(sender, "<gold><b>=== MythicMOTD Help ===</b></gold>");
        MessageUtils.sendMessage(sender,
                "<gray>/mythicmotd version</gray> <white>-</white> <aqua>Show plugin version</aqua>");
        MessageUtils.sendMessage(sender,
                "<gray>/mythicmotd reload</gray> <white>-</white> <aqua>Reload configuration</aqua>");

        return true;
    }

    private boolean handleVersion(CommandSender sender) {
        String version = plugin.getPluginMeta().getVersion();
        String authors = String.join(", ", plugin.getPluginMeta().getAuthors());

        MessageUtils.sendMessage(sender,
                "<green>MythicMOTD</green> <gray>version:</gray> <yellow>" + version + "</yellow>");
        MessageUtils.sendMessage(sender, "<gray>Developed by</gray> <yellow>" + authors + "</yellow>");

        return true;
    }

    private boolean handleReload(CommandSender sender) {
        if (!sender.hasPermission("mythicmotd.admin")) {
            MessageUtils.sendPermissionMessage(sender);
            return true;
        }

        try {
            plugin.getConfigManager().loadConfig();
            String reloadMessage = plugin.getConfigManager().getMessage("messages.admin");
            MessageUtils.sucessMessage(sender,
                    reloadMessage != null ? reloadMessage : "Configuration reloaded successfully!");
        } catch (Exception e) {
            MessageUtils.denyMessage(sender, "Failed to reload configuration: " + e.getMessage());
            plugin.getLogger().severe("Error reloading configuration: " + e.getMessage());
        }

        return true;
    }
}