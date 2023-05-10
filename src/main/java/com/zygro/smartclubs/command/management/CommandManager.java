package com.zygro.smartclubs.command.management;

import com.zygro.smartclubs.SmartClubs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private SmartClubs pl;
    private ArrayList<BaseCommand> baseCommands;

    public CommandManager(SmartClubs plugin) {
        this.pl = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        BaseCommand base = null;
        for (BaseCommand c : this.baseCommands) {
            if (c.checkCmdMatch(label)) {
                base = c;
            }
        }
        if (base == null) {
            return true;
        }

        PluginCommand cmd = null;
        if (base.subCommands.isEmpty()) {
            cmd = base;
        } else {
            cmd = findSubCommand(base, args);
        }
        if (cmd == null) {
            return true;
        }
        if (!checkPermission(commandSender, cmd)) {

        }

        return false;
    }

    private PluginCommand findSubCommand(PluginCommand base, String[] args) {
        if(args.length == 0) return base;
        PluginCommand nextCommand = getSubCommandFromLabel(base.subCommands, args[0]);
        if(nextCommand == null) return base;
        return findSubCommand(nextCommand, popFirstArgument(args));
    }

    private SubCommand getSubCommandFromLabel(ArrayList<SubCommand> cmds, String label) {
        for (SubCommand c : cmds) {
            if (c.checkCmdMatch(label)) {
                return c;
            }
        }
        return null;
    }

    private boolean checkPermission(CommandSender sender, String cmd) {
        return sender.hasPermission(cmd);
    }
}