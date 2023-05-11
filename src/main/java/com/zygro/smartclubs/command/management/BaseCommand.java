package com.zygro.smartclubs.command.management;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseCommand extends PluginCommand {

    protected ArrayList<String> aliases;

    public BaseCommand(String cmd, String permission, String syntax) {
        super(cmd, permission, syntax);

        this.aliases = new ArrayList<>();
        this.aliases.add(cmd);
    }
    @Override
    public abstract void execute(CommandSender sender, String[] args);

    @Override
    protected boolean checkCmdMatch(String cmd) {
        for (String alias : aliases) {
            if (cmd.equalsIgnoreCase(alias)) {
                return true;
            }
        }
        return false;
    }

}
