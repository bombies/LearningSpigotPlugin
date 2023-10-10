package me.bombies.learningplugin.utils.menus;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MenuBuilder {
    private Player owner;
    private String title;
    private MenuSize size;
    private boolean withPlaceHolders;
    private Material placeHolderMaterial;

    private final List<MenuItem> items = new ArrayList<>();

    public static MenuBuilder start() {
        return new MenuBuilder();
    }

    public MenuBuilder owner(Player owner) {
        this.owner = owner;
        return this;
    }

    public MenuBuilder title(String title) {
        this.title = MessageUtils.color(title);
        return this;
    }

    public MenuBuilder size(MenuSize size) {
        this.size = size;
        return this;
    }

    public MenuBuilder placeHolders(boolean withPlaceHolders) {
        this.withPlaceHolders = withPlaceHolders;
        return this;
    }

    public MenuBuilder placeHolderMaterial(Material placeHolderMaterial) {
        this.placeHolderMaterial = placeHolderMaterial;
        return this;
    }

    public MenuBuilder addItem(Function<MenuItemBuilder, MenuItemBuilder> itemBuilderCallback) {
        final var builder = MenuItemBuilder.start();
        final var item = itemBuilderCallback.apply(builder).build();
        this.items.add(item);
        return this;
    }

    public Menu build() {
        if (owner == null)
            throw new IllegalStateException("Menu owner must be set.");
        if (title == null)
            throw new IllegalStateException("Menu title must be set.");
        if (size == null)
            throw new IllegalStateException("Menu size must be set.");
        return new Menu(owner, title, size, withPlaceHolders, placeHolderMaterial, items);
    }

}
