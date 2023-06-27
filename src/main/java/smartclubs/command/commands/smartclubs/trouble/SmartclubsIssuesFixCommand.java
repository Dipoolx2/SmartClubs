package smartclubs.command.commands.smartclubs.trouble;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import smartclubs.SmartClubs;
import smartclubs.command.management.SubCommand;

public class SmartclubsIssuesFixCommand extends SubCommand {
    public SmartclubsIssuesFixCommand() {
        super("fix", SmartClubs.PERM_BASE+".admin", "/smartclubs issues fix");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (SmartClubs.INSTANCE.issueFixer.issues.size() < 1) {
            sender.sendMessage(ChatColor.GREEN + "There are no issues to fix! To look for any, run " + ChatColor.YELLOW + "/sc trouble");
            return;
        }
        sender.sendMessage(ChatColor.YELLOW + "Attempting to fix issues...");
        if (!SmartClubs.INSTANCE.issueFixer.fixIssues()) {
            sender.sendMessage(ChatColor.RED + "Couldn't fix all issues (" + SmartClubs.INSTANCE.issueFixer.issues.size() + " remaining). Please try again, or contact me on twitter at @o_zygro.");
            return;
        }
        sender.sendMessage(ChatColor.GREEN + "Fixed all issues! " + ChatColor.GRAY + "If you're still experiencing problems, try running the troubleshooter again.");
    }
}
