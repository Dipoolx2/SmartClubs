package smartclubs.command.commands.smartclubs.trouble;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import smartclubs.SmartClubs;
import smartclubs.command.management.SubCommand;

public class SmartclubsIssuesCommand extends SubCommand {

    public SmartclubsIssuesCommand() {
        super("issues", SmartClubs.PERM_BASE+".admin", "/smartclubs issues");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.YELLOW + "Looking for issues...");
        int issueCount = 0;
        // Find issues

    }
}
