package smartclubs.command.management;

import smartclubs.SmartClubs;
import smartclubs.command.commands.TestCommand;
import smartclubs.command.commands.TestTwoCommand;
import smartclubs.command.commands.group.GroupCreate;
import smartclubs.command.commands.group.GroupJoin;
import smartclubs.command.commands.grouptype.TypeCreate;
import org.bukkit.ChatColor;
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

        TypeCreate typeCreateCommand = new TypeCreate();
        this.baseCommands.add(typeCreateCommand);

        GroupCreate groupCreateCommand = new GroupCreate();
        this.baseCommands.add(groupCreateCommand);
        GroupJoin groupJoinCommand = new GroupJoin();
        this.baseCommands.add(groupJoinCommand);
    }

    private void registerCommands() {
        for (BaseCommand baseCommand : this.baseCommands) {
            for (String alias : baseCommand.aliases) {
                Objects.requireNonNull(pl.getCommand(alias)).setExecutor(this);
            }
        }
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
            commandSender.sendMessage(ChatColor.RED + "You're not authorized to run this command.");
            return true;
        }
        try {
            cmd.execute(commandSender, args);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            commandSender.sendMessage("&cImproper usage of command (" + base.syntax + ").");
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