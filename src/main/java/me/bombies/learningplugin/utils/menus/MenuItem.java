package me.bombies.learningplugin.utils.menus;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

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

    public String getCustomId() {
        return customId;
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
        final var itemBuilder = ItemBuilder.start()
                .material(material)
                .amount(amount)
                .name(name);

        if (lore != null)
            itemBuilder.lore(lore);

        if (isEnchanted) {
            itemBuilder.enchant(Enchantment.CHANNELING, 1)
                    .hideUnbreakable()
                    .hideAttributes()
                    .hideEnchants();
        }

        if (customId != null)
            itemBuilder.customId(MENU_ITEM_KEY, customId);
        return itemBuilder.build();
    }
}
