package br.goncalviz.essentialsgui.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReflectionUtils {

    public Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();

        }
        return null;
    }

    public void sendPacket(Player player, Object packet) throws Exception {
        Object getHandle = player.getClass().getMethod("getHandle").invoke(player);
        Object playerConnection = getHandle.getClass().getField("playerConnection").get(getHandle);
        playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);

    }
}
