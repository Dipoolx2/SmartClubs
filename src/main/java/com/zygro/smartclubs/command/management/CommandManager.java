package com.zygro.smartclubs.command.management;

import com.zygro.smartclubs.SmartClubs;
import com.zygro.smartclubs.command.impl.TestCommand;
import com.zygro.smartclubs.command.impl.TestTwoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CommandManager implements CommandExecutor {

    private SmartClubs pl;
    private final ArrayList<BaseCommand> baseCommands;

    public CommandManager(SmartClubs plugin) {
        this.pl = plugin;
        this.baseCommands = new ArrayList<>();
        this.initializeCommands();
        this.registerCommands();
    }

    private void initializeCommands() {
        TestCommand testCommand = new TestCommand();
        this.baseCommands.add(testCommand);
        TestTwoCommand testTwoCommand = new TestTwoCommand(testCommand);
        testCommand.subCommands.add(testTwoCommand);
    }

    private void registerCommands() {
        this.baseCommands.forEach(c -> {c.aliases.forEach(a -> Objects.requireNonNull(pl.getCommand(a)).setExecutor(this));});
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
        if (!cmd.isSenderAuthorized(commandSender)) {
            return true;
        }
        try {
            cmd.execute(commandSender, args);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            commandSender.sendMessage("&cInproper usage of command (" + base.syntax + ").");
        }

        return false;
    }

    private PluginCommand findSubCommand(PluginCommand base, String[] args) {
        if(args.length == 0) return base;
        PluginCommand nextCommand = getSubCommandFromLabel(base.subCommands, args[0]);
        if(nextCommand == null) return base;
        return findSubCommand(nextCommand, popFirstArgument(args));
    }
    private String[] popFirstArgument(String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }

    private SubCommand getSubCommandFromLabel(ArrayList<SubCommand> cmds, String label) {
        for (SubCommand c : cmds) {
            if (c.checkCmdMatch(label)) {
                return c;
            }
        }
        return null;
    }

}