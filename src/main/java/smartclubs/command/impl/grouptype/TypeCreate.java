package smartclubs.command.impl.grouptype;

import smartclubs.SmartClubs;
import smartclubs.command.management.BaseCommand;
import smartclubs.group.management.GroupManager;
import smartclubs.group.management.GroupType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TypeCreate extends BaseCommand {
    public TypeCreate() {
        super("typecreate", SmartClubs.PERM_BASE + ".typecreate", "/typecreate <name>");
        aliases.add("tcreate");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        GroupManager gm = SmartClubs.INSTANCE.groupManager;
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Improper usage of command (" + syntax + ").");
            return;
        }

        String groupTypeName = concatenateArgs(args);

        if (gm.isGroupTypeNameTaken(groupTypeName)) {
            sender.sendMessage(ChatColor.RED + "Group type with name " + ChatColor.YELLOW + groupTypeName + ChatColor.RED + " already exists.");
            return;
        }

        GroupType newGroupType = new GroupType(groupTypeName);
        gm.addGroupType(newGroupType);

        sender.sendMessage(ChatColor.GREEN + "Group type " + ChatColor.YELLOW + groupTypeName + ChatColor.GREEN + " has been successfully created.");
    }

    private String concatenateArgs(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
