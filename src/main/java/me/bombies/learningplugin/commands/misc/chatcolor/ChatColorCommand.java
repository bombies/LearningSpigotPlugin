package me.bombies.learningplugin.commands.misc.chatcolor;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class ChatColorCommand extends PlayerCommand {

    public ChatColorCommand() {
        super("chatcolor");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        if (player.getEffectivePermissions()
                .stream()
                .filter(permission -> permission.getPermission().startsWith("learningplugin.chatcolor."))
                .findFirst()
                .orElse(null)
                == null) {
            player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
            return true;
        }

        final var menu = new ChatColorMenu(player);
        player.openInventory(menu.getInventory());

        return true;
    }
}
