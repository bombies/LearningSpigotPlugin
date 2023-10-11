package me.bombies.learningplugin.utils.items;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ItemUtils {

    public static String getCustomId(ItemStack item) {
        final var itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        return itemMeta.getPersistentDataContainer().get(ItemBuilder.CUSTOM_ITEM_KEY, PersistentDataType.STRING);
    }

    public static String getCustomId(NamespacedKey customIdKey, ItemStack item) {
        final var itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        return itemMeta.getPersistentDataContainer().get(customIdKey, PersistentDataType.STRING);
    }

    public static String getCustomId(String customIdKey, ItemStack item) {
        final var itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(LearningPlugin.core, customIdKey), PersistentDataType.STRING);
    }
}
