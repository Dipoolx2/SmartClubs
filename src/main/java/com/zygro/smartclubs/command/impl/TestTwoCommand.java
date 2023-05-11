package com.zygro.smartclubs.command.impl;

import com.zygro.smartclubs.SmartClubs;
import com.zygro.smartclubs.command.management.PluginCommand;
import com.zygro.smartclubs.command.management.SubCommand;
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
