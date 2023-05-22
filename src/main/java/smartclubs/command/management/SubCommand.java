package smartclubs.command.management;

import org.bukkit.command.CommandSender;

public abstract class SubCommand extends PluginCommand {
    public SubCommand(String cmd, String permission, String syntax) {
        super(cmd, permission, syntax);
    }
    @Override
    public abstract void execute(CommandSender sender, String[] args);

    @Override
    protected boolean checkCmdMatch(String cmd) {
        return this.cmd.equalsIgnoreCase(cmd);
    }

}
