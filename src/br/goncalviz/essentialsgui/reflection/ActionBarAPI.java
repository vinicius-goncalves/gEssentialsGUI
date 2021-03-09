package br.goncalviz.essentialsgui.reflection;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class ActionBarAPI implements ReflectionInterface {

    private ReflectionUtils reflectionUtils = new ReflectionUtils();

    @Override
    public void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) throws Exception {

    }

    @Override
    public void sendActionBarToPlayer(Player player, String message) throws Exception {

        Constructor<?> constructorActionBar = reflectionUtils.getNMSClass("PacketPlayOutChat").getConstructor(
                reflectionUtils.getNMSClass("IChatBaseComponent"), byte.class);

        Object iChatBaseComponent = reflectionUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\""+message+"\"}");
        Object packetPlayOutChat = constructorActionBar.newInstance(iChatBaseComponent, (byte) 2);

        reflectionUtils.sendPacket(player, packetPlayOutChat);

    }
}
