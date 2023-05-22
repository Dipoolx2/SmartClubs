package smartclubs.command.commands.group;

import smartclubs.SmartClubs;
import smartclubs.command.management.BaseCommand;
import smartclubs.group.management.GroupManager;
import smartclubs.group.management.GroupType;
import smartclubs.profile.ProfileManager;
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
        ProfileManager pm = SmartClubs.INSTANCE.profileManager;
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Improper usage of command (" + syntax + ").");
            return;
        }
        GroupJoin.NameAndCount typeName = getTypeName(args);
        GroupType groupType = gm.getGroupTypeFromName(typeName.str);
        if (groupType == null) {
            sender.sendMessage(ChatColor.RED + "Group type " + ChatColor.YELLOW + typeName.str + ChatColor.RED + " does not exist. Use quotation marks (\" or ') when using spaces.");
            return;
        }
        String[] argsWithoutType = shortenArray(args, typeName.count);
        String groupName = String.join(" ", argsWithoutType);

        if (gm.addProfileToGroup(groupName, groupType, pm.getPlayerProfile((OfflinePlayer)sender))) {
            sender.sendMessage(ChatColor.GREEN + "Joined " + ChatColor.YELLOW + typeName.str + ChatColor.GREEN + " named " + ChatColor.YELLOW + groupName + ChatColor.GREEN + "!");
        } else {
            sender.sendMessage(ChatColor.RED + "Couldn't join group. Please try again later.");
        }

    }

    private String[] shortenArray(String[] arr, int typeLength) {
        String[] result = new String[arr.length - typeLength];
        if (arr.length - typeLength >= 0)
            System.arraycopy(arr, typeLength, result, 0, arr.length - typeLength);
        return result;
    }

    private GroupJoin.NameAndCount getTypeName(String[] args) {
        char openingQuote = '!';
        int wordCount = 0;
        if(args[0].charAt(0) == '\'' || args[0].charAt(0) == '"') {
            wordCount++;
            openingQuote = args[0].charAt(0);
        } else {
            return new GroupJoin.NameAndCount(args[0], 1);
        }
        char closingQuote = '!';
        for (String word : args) {
            if (closingQuote != '!') break;
            if (word.equals(args[0])) continue;
            if (word.charAt(word.length() - 1) == '\'' || word.charAt(word.length() - 1) == '"') {
                closingQuote = word.charAt(word.length() - 1);
            }
            wordCount++;
        }
        if (closingQuote == '!' || (openingQuote != closingQuote)) return new GroupJoin.NameAndCount(args[0], 1);

        String[] updatedArgs = nameWithoutQuotes(args, wordCount);
        return new GroupJoin.NameAndCount(String.join(" ", updatedArgs), wordCount);
    }

    private String[] nameWithoutQuotes(String[] args, int wordCount) {
        if (args.length == 0) return new String[0];
        String[] result = new String[wordCount];
        System.arraycopy(args, 0, result, 0, wordCount);
        result[0] = result[0].substring(1);
        result[wordCount - 1] = result[wordCount - 1].substring(0, result[wordCount - 1].length()-1);
        return result;
    }

    private static class NameAndCount {
        public String str;
        public int count;
        private NameAndCount(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
