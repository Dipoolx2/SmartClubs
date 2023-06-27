package smartclubs.command.commands.group;

import smartclubs.SmartClubs;
import smartclubs.command.commands.CommandUtils;
import smartclubs.command.management.BaseCommand;
import smartclubs.data.DataManager;
import smartclubs.group.management.GroupManager;
import smartclubs.group.management.GroupType;
import smartclubs.profile.PlayerProfile;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupJoin extends BaseCommand {


    public GroupJoin() {
        super("groupjoin", SmartClubs.PERM_BASE+".groupjoin", "/groupjoin <type> <name>");
        aliases.add("gjoin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You need to be a player to execute this command!");
            return;
        }

        GroupManager gm = SmartClubs.INSTANCE.groupManager;
        DataManager dm = SmartClubs.INSTANCE.dataManager;

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Improper usage of command (" + syntax + ").");
            return;
        }

        CommandUtils.NameAndCount typeName = CommandUtils.getTypeName(args);
        GroupType groupType = gm.getGroupTypeFromName(typeName.str);
        if (groupType == null) {
            sender.sendMessage(ChatColor.RED + "Group type " + ChatColor.YELLOW + typeName.str + ChatColor.RED +
                    " does not exist. Use quotation marks (\" or ') when using spaces.");
            return;
        }

        String groupName = String.join(" ", CommandUtils.shortenArray(args, typeName.count));
        PlayerProfile profile = dm.getPlayerProfile((OfflinePlayer) sender);
        if (gm.addProfileToGroup(groupName, groupType, profile)) {
            sender.sendMessage(ChatColor.GREEN + "Joined " + ChatColor.YELLOW + typeName.str + ChatColor.GREEN +
                    " named " + ChatColor.YELLOW + groupName + ChatColor.GREEN + "!");
        } else {
            sender.sendMessage(ChatColor.RED + "Couldn't join " + typeName.str.toLowerCase() +
                    ". Are you already in it?");
        }
    }

}
