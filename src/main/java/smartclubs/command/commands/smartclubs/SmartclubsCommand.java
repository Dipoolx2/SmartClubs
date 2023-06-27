package smartclubs.command.commands.smartclubs;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import smartclubs.SmartClubs;
import smartclubs.command.management.BaseCommand;

public class SmartclubsCommand extends BaseCommand {
    public SmartclubsCommand() {
        super("smartclubs", SmartClubs.PERM_BASE+".admin", "/smartclubs [arguments]");
        this.aliases.add("sc");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.BLUE+ChatColor.BOLD.toString()+"---------------------------------------------");
            sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString()+"                 SMARTCLUBS HELP");
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.YELLOW + "/smartclubs issues [fix]  " + ChatColor.GRAY + "Attempts to find common setup issues.");
            sender.sendMessage(ChatColor.BLUE+ChatColor.BOLD.toString()+"---------------------------------------------");
            return;
        }
        sender.sendMessage(ChatColor.RED + "That is not a valid sub-command. Run /smartclubs for help.");
    }
}
