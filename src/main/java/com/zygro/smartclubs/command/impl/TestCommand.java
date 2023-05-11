package com.zygro.smartclubs.command.impl;

import com.zygro.smartclubs.SmartClubs;
import com.zygro.smartclubs.command.management.BaseCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TestCommand extends BaseCommand {
    public TestCommand() {
        super("test", SmartClubs.PERM_BASE+".test", "/test");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "The command works!");
    }
}
