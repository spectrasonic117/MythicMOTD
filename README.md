# MythicMOTD Plugin

Este plugin para Minecraft permite personalizar y gestionar mensajes de bienvenida y otros mensajes en el servidor mediante comandos y configuraciones fáciles de modificar. Utiliza MiniMessage para formatear mensajes de forma avanzada y flexible, permitiendo una personalización visual atractiva y dinámica.

## Comandos de uso

`/motd reload`: recarga la configuración del plugin desde el archivo config.yml.

`/motd version`: muestra la versión del plugin.

## Introducción a MiniMessage
MiniMessage es una librería de Adventure que permite crear mensajes con formato avanzado mediante un sistema de etiquetas cortas y legibles. Ejemplo básico:

```txt
<red>Este texto es rojo</red> y <bold>este en negrita</bold>.
```

## Cómo funciona MiniMessage
Las etiquetas `<tag>` se colocan alrededor del texto que deseas formatear.
Puedes combinar varias etiquetas para lograr efectos complejos.
La librería interpreta estas etiquetas y las convierte en componentes visuales en el chat del juego.

## Modificación de la configuración del plugin
La configuración se realiza en el archivo config.yml. Puedes cambiar el mensaje predeterminado, los colores, y otros parámetros. Ejemplo:

```yaml
server_motd:
    line_1: "<c><gradient:#3f368e:#ffdd5c><b>Mythic MOTD</b></gradient></c>"
    line_2: "<c><rainbow><b>MiniMessage</rainbow> <#a8baff><b>Support</b></#a8baff></c>"
```

### Para modificar la configuración:

1. Abre config.yml.
2. Edita los valores en la sección motd.
3. Guarda el archivo.
4. Ejecuta /motd reload en el juego para aplicar los cambios.

### Notas adicionales
Para crear mensajes personalizados, combina etiquetas MiniMessage según tus preferencias.

Puedes consultar la documentación oficial de Adventure MiniMessage para más etiquetas y efectos avanzados.