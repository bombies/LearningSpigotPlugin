package me.bombies.learningplugin.utils.menus;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class MenuItemBuilder {
    private Material material;
    private String customId;

    private String name;
    private List<String> lore;
    private boolean isEnchanted;
    private int slot;
    private int amount = 1;

    static MenuItemBuilder start() {
        return new MenuItemBuilder();
    }

    public MenuItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public MenuItemBuilder name(String name) {
        this.name = MessageUtils.color(name);
        return this;
    }

    public MenuItemBuilder customId(String customId) {
        this.customId = customId;
        return this;
    }

    public MenuItemBuilder lore(String... lore) {
        this.lore = Arrays.stream(lore).map(MessageUtils::color).toList();
        return this;
    }

    public MenuItemBuilder isEnchanted(boolean isEnchanted) {
        this.isEnchanted = isEnchanted;
        return this;
    }

    public MenuItemBuilder slot(int slot) {
        this.slot = slot;
        return this;
    }

    public MenuItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public MenuItem build() {
        if (material == null)
            throw new IllegalStateException("Menu item material cannot be null");
        if (name == null)
            throw new IllegalStateException("Menu name cannot be null");
        return new MenuItem(material, customId, name, lore, isEnchanted, slot, amount);
    }
}
