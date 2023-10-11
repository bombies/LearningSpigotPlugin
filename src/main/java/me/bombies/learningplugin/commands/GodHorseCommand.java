package me.bombies.learningplugin.commands;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;

public class GodHorseCommand extends PlayerCommand {
    public GodHorseCommand() {
        super("godhorse");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();

        if (!player.hasPermission(Permissions.GOD_HORSE)) {
            player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
            return true;
        }

        final var playerLocation = player.getLocation();
        final var playerWorld = playerLocation.getWorld();
        assert playerWorld != null;

        final var godHorse = (Horse) playerWorld.spawnEntity(playerLocation, EntityType.HORSE);

        godHorse.setGlowing(true);
        godHorse.setOwner(player);
        godHorse.setInvulnerable(true);
        godHorse.setPersistent(true);
        godHorse.addPassenger(player);
        godHorse.setStyle(Horse.Style.WHITE);
        godHorse.getInventory().setArmor(new ItemStack(Material.GOLDEN_HORSE_ARMOR));
        godHorse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        godHorse.setCustomName(MessageUtils.color("&f&l[&e&l&k@@&f&l]&r &6&lGOD HORSE &f&l[&e&l&k@@&f&l]"));
        godHorse.setCustomNameVisible(true);
        godHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        godHorse.setJumpStrength(2);

        final var godHorseData = new PersistentDataHandler(godHorse);
        godHorseData.set("owner", player.getUniqueId().toString());

        player.playSound(playerLocation, Sound.ENTITY_PLAYER_LEVELUP, 5f, 1f);
        playerWorld.strikeLightningEffect(playerLocation);

        player.sendMessage(MessageUtils.color("&a You have been given a &f&l[&e&l&k@@&f&l]&r &6&lGOD HORSE &f&l[&e&l&k@@&f&l]&a!"));
        return true;
    }
}
