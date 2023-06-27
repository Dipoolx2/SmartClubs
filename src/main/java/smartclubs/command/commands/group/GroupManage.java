package smartclubs.command.commands.group;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import smartclubs.SmartClubs;
import smartclubs.command.commands.CommandUtils;
import smartclubs.command.management.BaseCommand;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupManager;
import smartclubs.group.management.GroupType;
import smartclubs.group.management.GroupTypeData;
import smartclubs.gui.impl.GroupConfigurationInventory;
import smartclubs.profile.PlayerProfile;

import java.util.List;
import java.util.UUID;

public class GroupManage extends BaseCommand {
    public GroupManage() {
        super("groupmanage", "smartclubs.groupmanage", "/groupmanage <group-type>");
        aliases.add("gmanage");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Manage your groups from in-game, or from your data storage service directly.");
            return;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Improper usage of command (" + syntax + ").");
            return;
        }
        GroupManager gm = SmartClubs.INSTANCE.groupManager;

        CommandUtils.NameAndCount typeName = CommandUtils.getTypeName(args);
        GroupType groupType = gm.getGroupTypeFromName(typeName.str);
        if (groupType == null) {
            sender.sendMessage(ChatColor.RED + "Group type " + ChatColor.YELLOW + typeName.str + ChatColor.RED +
                    " does not exist. Use quotation marks (\" or ') when using spaces.");
            return;
        }
        List<UUID> availableGroups = SmartClubs.INSTANCE.dataManager.getPlayerGroupsByType(((Player)sender).getUniqueId(), groupType.uniqueId);
        if (availableGroups.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "You aren't authorized to manage any groups of that type.");
            return;
        }
        PlayerProfile playerProfile = SmartClubs.INSTANCE.dataManager.getPlayerProfile((Player) sender);
        Group groupToManage = SmartClubs.INSTANCE.dataManager.getGroupData(groupType.uniqueId, availableGroups.get(0));
        if (groupToManage == null) {
            sender.sendMessage(ChatColor.RED + "An issue occurred when retrieving the group's data. Please contact a server administrator.");
            return;
        }
        GroupConfigurationInventory configurationInventory = new GroupConfigurationInventory(groupToManage, playerProfile);
        configurationInventory.openInventory(playerProfile);
    }

}
