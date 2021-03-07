package br.goncalviz.essentialsgui.reflection;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class TitlesAPI {

    private final ReflectionUtils reflectionUtils = new ReflectionUtils();

    public void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) throws Exception {
        Object titleObject = reflectionUtils.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
        Object subtitleObject = reflectionUtils.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);

        Object titleChat = reflectionUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\""+title+"\"}");
        Object subtitleChat = reflectionUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");

        Constructor<?> titleConstructor = reflectionUtils.getNMSClass("PacketPlayOutTitle").getConstructor(reflectionUtils.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                reflectionUtils.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);

        Object titlePacket = titleConstructor.newInstance(titleObject, titleChat, fadeIn, stay, fadeOut);
        Object subtitlePacket = titleConstructor.newInstance(subtitleObject, subtitleChat, fadeIn, stay, fadeOut);

        reflectionUtils.sendPacket(player, titlePacket);
        reflectionUtils.sendPacket(player, subtitlePacket);

    }
}
