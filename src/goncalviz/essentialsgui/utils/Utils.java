package goncalviz.essentialsgui.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
}
