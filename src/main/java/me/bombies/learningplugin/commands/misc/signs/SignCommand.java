package me.bombies.learningplugin.commands.misc.signs;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;

public class SignCommand extends PlayerCommand {
    public SignCommand() {
        super("sign");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var world = player.getWorld();

        final var signBlock = world.getBlockAt(player.getLocation());
        signBlock.setType(Material.OAK_SIGN);

        final var sign = (Sign) signBlock.getState();
        final var signFront = sign.getSide(Side.FRONT);
        final var signBack = sign.getSide(Side.BACK);

        final var signData = new PersistentDataHandler(sign);
        signData.set("custom_sign", true);
        signData.set("custom_sign_owner", player.getUniqueId().toString());

        if (!commandContext.getArgs().isEmpty()) {
            final var text = String.join(" ", commandContext.getArgs().all());
            signFront.setLine(0, text);
            signBack.setLine(0, text);
        }

        sign.update();
        return true;
    }
}
