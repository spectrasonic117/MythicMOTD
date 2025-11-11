package com.spectrasonic.MythicMOTD.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MOTDUtils {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final PlainTextComponentSerializer plainSerializer = PlainTextComponentSerializer.plainText();

    // Ancho estándar de línea para centrar (aproximadamente el ancho típico de la
    // pantalla de servidor)
    private static final int LINE_WIDTH = 59;

    public static Component processMOTDLine(String line) {
        // Procesar etiquetas de centrado <center> o <c>
        String processedLine = processCenterTags(line);

        // Procesar con MiniMessage
        return miniMessage.deserialize(processedLine);
    }

    private static String processCenterTags(String line) {
        // Patrón para encontrar etiquetas <center>...</center> o <c>...</c>
        Pattern centerPattern = Pattern.compile("<c(enter)?>\\s*(.*?)\\s*</c(enter)?>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = centerPattern.matcher(line);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String content = matcher.group(2);
            String centeredContent = centerText(content);
            // Escapar caracteres especiales para la sustitución
            centeredContent = Matcher.quoteReplacement(centeredContent);
            matcher.appendReplacement(result, centeredContent);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private static String centerText(String text) {
        try {
            // Primero procesamos el texto con MiniMessage para obtener el componente
            Component component = miniMessage.deserialize(text);
            // Luego obtenemos la representación de texto plano para calcular la longitud
            String plainText = plainSerializer.serialize(component);

            // Calcular el número de espacios necesarios para centrar
            int textLength = plainText.length();
            int spacesNeeded = Math.max(0, (LINE_WIDTH - textLength) / 2);

            // Crear la cadena con espacios
            StringBuilder centered = new StringBuilder();
            for (int i = 0; i < spacesNeeded; i++) {
                centered.append(" ");
            }
            centered.append(text);

            return centered.toString();
        } catch (Exception e) {
            // Si hay un error en el procesamiento, devolver el texto original
            return text;
        }
    }
}