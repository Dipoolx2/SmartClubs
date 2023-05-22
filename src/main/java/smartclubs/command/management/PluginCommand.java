package smartclubs.command.management;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public abstract class PluginCommand {

    protected final String cmd;
    private final String permission;
    protected final String syntax;

    protected final ArrayList<SubCommand> subCommands;

    public PluginCommand(String cmd, String permission, String syntax) {
        this.cmd = cmd;
        this.permission = permission;
        this.syntax = syntax;

        this.subCommands = new ArrayList<>();
    }

    public abstract void execute(CommandSender sender, String[] args);

    protected abstract boolean checkCmdMatch(String cmd);

    protected boolean isSenderAuthorized(CommandSender sender) {
        return sender.hasPermission(this.permission);
    }

}
