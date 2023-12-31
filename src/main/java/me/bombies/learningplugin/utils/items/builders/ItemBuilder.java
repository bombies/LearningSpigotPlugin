package me.bombies.learningplugin.utils.items.builders;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.classes.Pair;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemBuilder {
    public static final NamespacedKey CUSTOM_ITEM_KEY = new NamespacedKey(LearningPlugin.core, "custom-item-id");

    private Material material;
    private NamespacedKey customIdKey = CUSTOM_ITEM_KEY;
    private String customId;

    private String name;
    private List<String> lore;
    private int amount = 1;
    private HashMap<Enchantment, Integer> enchants = new HashMap<>();
    private HashSet<ItemFlag> itemFlags = new HashSet<>();
    private boolean isUnbreakable = false;
    private HashMap<String, String> metaData = new HashMap<>();

    public static ItemBuilder start() {
        return new ItemBuilder();
    }

    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder customId(String customId) {
        this.customId = customId;
        return this;
    }

    public ItemBuilder customId(String customIdKey, String customId) {
        this.customIdKey = new NamespacedKey(LearningPlugin.core, customIdKey);
        this.customId = customId;
        return this;
    }

    public ItemBuilder customId(NamespacedKey customIdKey, String customId) {
        this.customIdKey = customIdKey;
        this.customId = customId;
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = MessageUtils.color(name);
        return this;
    }

    public ItemBuilder lore(String... lore) {
        this.lore = Arrays.stream(lore).map(MessageUtils::color).toList();
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore.stream().map(MessageUtils::color).toList();
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    @SafeVarargs
    public final ItemBuilder enchants(Pair<Enchantment, Integer>... enchants) {
        final var enchantMap = new HashMap<Enchantment, Integer>();
        Arrays.stream(enchants).forEach(enchant -> enchantMap.put(enchant.getLeft(), enchant.getRight()));
        this.enchants = enchantMap;
        return this;
    }

    public ItemBuilder enchant(Enchantment enchant, int level) {
        this.enchants.put(enchant, level);
        return this;
    }

    public ItemBuilder itemFlags(ItemFlag... itemFlags) {
        this.itemFlags = new HashSet<>(Arrays.asList(itemFlags));
        return this;
    }

    public ItemBuilder hideEnchants() {
        itemFlags.add(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder hideAttributes() {
        itemFlags.add(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }

    public ItemBuilder hideUnbreakable() {
        itemFlags.add(ItemFlag.HIDE_UNBREAKABLE);
        return this;
    }

    public ItemBuilder unbreakable() {
        this.isUnbreakable = true;
        return this;
    }

    @SafeVarargs
    public final ItemBuilder metaData(Pair<String, String>... metaData) {
        final var metaMap = new HashMap<String, String>();
        Arrays.stream(metaData).forEach(meta -> metaMap.put(meta.getLeft(), meta.getRight()));
        this.metaData = metaMap;
        return this;
    }

    public ItemBuilder metaData(String key, String value) {
        this.metaData.put(key, value);
        return this;
    }


    public ItemStack build() {
        if (material == null)
            throw new IllegalStateException("Item material cannot be null");

        final var item = new ItemStack(material, amount);
        final var itemMeta = item.getItemMeta();
        assert itemMeta != null;

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        enchants.forEach((enchant, level) -> itemMeta.addEnchant(enchant, level, true));
        itemMeta.addItemFlags(itemFlags.toArray(ItemFlag[]::new));
        itemMeta.setUnbreakable(isUnbreakable);

        final var persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (customId != null)
            persistentDataContainer.set(customIdKey, PersistentDataType.STRING, customId);

        if (metaData != null && !metaData.isEmpty()) {
            metaData.forEach((key, value) -> {
                final var nameSpacedKey = new NamespacedKey(LearningPlugin.core, key);
                persistentDataContainer.set(nameSpacedKey, PersistentDataType.STRING, value);
            });
        }

        item.setItemMeta(itemMeta);
        return item;
    }
}
