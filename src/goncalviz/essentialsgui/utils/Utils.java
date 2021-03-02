package goncalviz.essentialsgui.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class Utils {

    public String withColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);

    }

    public ItemStack setItem(Material material, int amount, int data, String displayName, String[] lore) {
        ItemStack itemStack = new ItemStack(material, amount, (short) data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.withColor(displayName));
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    public ItemStack setSkull(String displayName, String ownerSkull, String[] lore) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setDisplayName(withColor(displayName));
        skullMeta.setOwner(ownerSkull);
        skullMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(skullMeta);
        return itemStack;

    }

    public void sendActionbarToTarget(Player player, String message) {
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a(withColor("{\"text\":\"" + message + "\"}").replace('&', '§'));
        PacketPlayOutChat packetChat = new PacketPlayOutChat(chat, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetChat);

    }

    public void sendMessageToOpPlayer(String messageToOpPlayer) {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.isOnline() && player.isOp()).forEach(playerAction
        -> playerAction.sendMessage(withColor(messageToOpPlayer)));

    }

}
