package com.spectrasonic.MythicMOTD.commands;

import com.spectrasonic.MythicMOTD.Main;
import com.spectrasonic.MythicMOTD.utils.MessageUtils;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.command.CommandSender;
import dev.jorel.commandapi.CommandPermission;

public class MOTDCommand {

    private final Main plugin;

    public MOTDCommand(Main plugin) {
        this.plugin = plugin;
        registerCommand();
    }

    private void registerCommand() {
        new CommandAPICommand("mythicmotd")
                .withAliases("mmotd", "motd")
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission(CommandPermission.OP)
                        .executes((sender, args) -> {
                            executeReload(sender);
                        }))
                .withSubcommand(new CommandAPICommand("version")
                        .executes((sender, args) -> {
                            executeVersion(sender);
                        }))
                .register();
    }

    private void executeVersion(CommandSender sender) {
        String version = plugin.getPluginMeta().getVersion();
        String authors = String.join(", ", plugin.getPluginMeta().getAuthors());
        MessageUtils.sendMessage(sender,
                "<green>MythicMOTD</green> <gray>version:</gray> <yellow>" + version + "</yellow>");
        MessageUtils.sendMessage(sender, "<gray>Developed by</gray> <yellow>" + authors + "</yellow>");
    }

    private void executeReload(CommandSender sender) {
        plugin.getConfigManager().loadConfig();
        String reloadMessage = plugin.getConfigManager().getMessage("messages.reload");
        MessageUtils.sucessMessage(sender, reloadMessage);
    }
}