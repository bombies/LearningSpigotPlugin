package me.bombies.learningplugin.utils.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

public class Menu {

    private final String title;
    private final MenuSize size;
    private final boolean withPlaceHolders;
    private final Material placeHolderMaterial;

    private final List<MenuItem> items;

    Menu(String title, MenuSize size, boolean withPlaceHolders, @Nullable Material placeHolderMaterial, List<MenuItem> items) {
        this.title = title;
        this.size = size;
        this.withPlaceHolders = withPlaceHolders;
        this.placeHolderMaterial = placeHolderMaterial;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size.getSize();
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public Inventory toInventory() {
        final var menuInventory = Bukkit.createInventory(null, size.getSize(), title);

        for (final var item : items) {
            final var slot = item.getSlot();
            if (slot < 0 || slot >= size.getSize())
                throw new IllegalStateException("Menu item slot must be between 0 and " + size.getSize() + ". " + slot + " is not a valid slot.");
            menuInventory.setItem(item.getSlot(), item.toItemStack());
        }

        if (withPlaceHolders) {
            final var occupiedSlots = items.stream().map(MenuItem::getSlot).toList();
            IntStream.range(0, size.getSize())
                    .filter(slot -> !occupiedSlots.contains(slot))
                    .forEach(slot -> menuInventory.setItem(slot,
                                    MenuItemBuilder.start()
                                            .material(placeHolderMaterial != null ? placeHolderMaterial : Material.BLACK_STAINED_GLASS_PANE)
                                            .name("&l")
                                            .slot(slot)
                                            .build()
                                            .toItemStack()
                            )
                    );
        }

        return menuInventory;
    }
}
