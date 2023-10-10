package me.bombies.learningplugin.commands.utils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PlayerSubCommand {

    private PlayerCommand parentCommand;
    private CommandContext commandContext;
    private final String name;
    private final List<String> aliases;

    protected PlayerSubCommand(String name) {
        this.name = name;
        this.aliases = new ArrayList<>();
    }

    protected PlayerSubCommand(String name, String... aliases) {
        this.name = name;
        this.aliases = Arrays.stream(aliases)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    void setParentCommand(@Nonnull PlayerCommand parentCommand) {
        this.parentCommand = parentCommand;
    }

    void setCommandContext(@Nonnull CommandContext parentContext) {
        final CommandArgs oldArgs = parentContext.getArgs();
        final List<String> newArgs = oldArgs.all().subList(1, oldArgs.length());
        this.commandContext = new CommandContext(parentContext.getPlayer(), parentContext.getCommand(), parentContext.getLabel(), newArgs);
    }

    protected abstract void handle(CommandContext context);

    void execute() {
        handle(commandContext);
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }
}
