package me.bombies.learningplugin.commands.misc.spawner;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.GeneralUtils;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class SpawnerCommand extends PlayerCommand {
    public static final String CUSTOM_SPAWNER_ID = "custom_spawner";
    public static final String CUSTOM_SPAWNER_ENTITY_TYPE = "custom_spawner_entity";

    public SpawnerCommand() {
        super("spawner");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var args = commandContext.getArgs();

        if (args.isEmpty()) {
            player.sendMessage(MessageUtils.color("&cUsage: /spawner <mob> [count]"));
            return true;
        }

        final var mob = args.first().toUpperCase();
        final var countString = args.length() == 2 ? args.get(1) : "1";
        final int count;
        try {
            count = Integer.parseInt(countString);
        } catch (NumberFormatException e) {
            player.sendMessage(MessageUtils.color("&aYou must provide a valid number for the count!"));
            return true;
        }

        try {
            final var entityType = EntityType.valueOf(mob);
            final var spawner = buildSpawner(entityType, count);

            player.getInventory().addItem(spawner);
        } catch (IllegalArgumentException e) {
            player.sendMessage(MessageUtils.color("&cThere is no such mob with name: " + mob));
        }

        return true;
    }

    public static ItemStack buildSpawner(EntityType type, int count) {
        return ItemBuilder.start()
                .customId(CUSTOM_SPAWNER_ID)
                .amount(count)
                .name("&e&l" + GeneralUtils.capitalize(type.toString().replace("_", " ")) + " Spawner")
                .lore("&7A spawner that spawns " + type.toString().replace("_", " ").toLowerCase())
                .material(Material.SPAWNER)
                .enchant(Enchantment.SILK_TOUCH, 1)
                .hideEnchants()
                .hideUnbreakable()
                .hideAttributes()
                .metaData(CUSTOM_SPAWNER_ENTITY_TYPE, type.toString())
                .build();
    }
}
