package smartclubs.command.impl;

import smartclubs.SmartClubs;
import smartclubs.command.management.PluginCommand;
import smartclubs.command.management.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TestTwoCommand extends SubCommand {
    public TestTwoCommand(PluginCommand baseCommand) {
        super("two", SmartClubs.PERM_BASE+".test.two", "/test two");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.YELLOW + "And this works too!");
    }
}
