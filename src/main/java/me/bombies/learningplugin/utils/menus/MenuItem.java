package me.bombies.learningplugin.utils.menus;

import me.bombies.learningplugin.LearningPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.List;

public class MenuItem {

    public static final NamespacedKey MENU_ITEM_KEY = new NamespacedKey(LearningPlugin.core, "custom-menu-item-id");

    private final Material material;
    private final String customId;
    private final String name;
    private final List<String> lore;
    private final boolean isEnchanted;
    private final int slot;
    private int amount = 1;

    MenuItem(Material material, @Nullable String customId, String name, List<String> lore, boolean isEnchanted, int slot) {
        this.material = material;
        this.customId = customId;
        this.name = name;
        this.lore = lore;
        this.isEnchanted = isEnchanted;
        this.slot = slot;
    }

    MenuItem(Material material, @Nullable String customId, String name, List<String> lore, boolean isEnchanted, int slot, int amount) {
        this.material = material;
        this.customId = customId;
        this.name = name;
        this.lore = lore;
        this.isEnchanted = isEnchanted;
        this.slot = slot;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public boolean isEnchanted() {
        return isEnchanted;
    }

    public int getSlot() {
        return slot;
    }

    public int getAmount() {
        return amount;
    }

    public ItemStack toItemStack() {
        final var item = new ItemStack(material, amount);
        final var itemMeta = item.getItemMeta();
        assert itemMeta != null;

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        if (isEnchanted) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
            itemMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        }

        if (customId != null)
            itemMeta.getPersistentDataContainer().set(MENU_ITEM_KEY, PersistentDataType.STRING, customId);

        item.setItemMeta(itemMeta);
        return item;
    }
}