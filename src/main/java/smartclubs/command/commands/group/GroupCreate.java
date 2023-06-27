package smartclubs.command.commands.group;

import smartclubs.SmartClubs;
import smartclubs.command.commands.CommandUtils;
import smartclubs.command.management.BaseCommand;
import smartclubs.data.DataManager;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupManager;
import smartclubs.group.management.GroupType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import smartclubs.group.management.GroupTypeData;

public class GroupCreate extends BaseCommand {
    public GroupCreate() {
        super("groupcreate", SmartClubs.PERM_BASE+".gcreate", "/gcreate <type> <name>");
        aliases.add("gcreate");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        GroupManager gm = SmartClubs.INSTANCE.groupManager;
        DataManager dm = SmartClubs.INSTANCE.dataManager;
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Improper usage of command (" + syntax + ").");
            return;
        }
        CommandUtils.NameAndCount typeName = CommandUtils.getTypeName(args);
        GroupType groupType = gm.getGroupTypeFromName(typeName.str);
        if (groupType == null) {
            sender.sendMessage(ChatColor.RED + "Group type " + ChatColor.YELLOW + typeName.str + ChatColor.RED + " does not exist. Use quotation marks (\" or ') when using spaces.");
            return;
        }
        String[] argsWithoutType = CommandUtils.shortenArray(args, typeName.count);
        String groupName = String.join(" ", argsWithoutType);

        Group newGroup = new Group(groupName, new GroupTypeData(groupType).uniqueId);
        if (dm.isGroupNameTaken(groupType, groupName)) {
            sender.sendMessage(ChatColor.RED + "Group of type " + ChatColor.YELLOW + typeName.str + ChatColor.RED + " already exists with name " + ChatColor.YELLOW + groupName + ChatColor.RED + ".");
            return;
        }
        gm.registerGroupToCache(newGroup);
        dm.addGroupToData(newGroup);
        sender.sendMessage(ChatColor.GREEN + "Created a new " + ChatColor.YELLOW + typeName.str + ChatColor.GREEN + " named " + ChatColor.YELLOW + groupName + ChatColor.GREEN + ".");
    }

}
