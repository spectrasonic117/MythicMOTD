package com.spectrasonic.MythicMOTD.listeners;

import com.spectrasonic.MythicMOTD.Main;
import com.spectrasonic.MythicMOTD.utils.MOTDUtils;
import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MOTDListener implements Listener {
    
    private final Main plugin;
    
    public MOTDListener(Main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onServerListPing(PaperServerListPingEvent event) {
        // Obtener las líneas del MOTD desde la configuración
        String line1 = plugin.getConfigManager().getMessage("server_motd.line_1");
        String line2 = plugin.getConfigManager().getMessage("server_motd.line_2");
        
        // Procesar las líneas con MiniMessage y centrado
        Component motdLine1 = MOTDUtils.processMOTDLine(line1);
        Component motdLine2 = MOTDUtils.processMOTDLine(line2);
        
        // Crear el componente completo del MOTD
        Component motd = motdLine1.append(Component.newline()).append(motdLine2);
        
        // Establecer el MOTD en el evento
        event.motd(motd);
    }
}