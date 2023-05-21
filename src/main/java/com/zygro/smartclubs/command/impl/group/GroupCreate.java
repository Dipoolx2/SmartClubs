package com.zygro.smartclubs.command.impl.group;

import com.zygro.smartclubs.SmartClubs;
import com.zygro.smartclubs.command.management.BaseCommand;
import com.zygro.smartclubs.group.management.Group;
import com.zygro.smartclubs.group.management.GroupManager;
import com.zygro.smartclubs.group.management.GroupType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class GroupCreate extends BaseCommand {
    public GroupCreate() {
        super("groupcreate", SmartClubs.PERM_BASE+".gcreate", "/gcreate <type> <name>");
        aliases.add("gcreate");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        GroupManager gm = SmartClubs.INSTANCE.groupManager;
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Improper usage of command (" + syntax + ").");
            return;
        }
        NameAndCount typeName = getTypeName(args);
        GroupType groupType = gm.getGroupTypeFromName(typeName.str);
        if (groupType == null) {
            sender.sendMessage(ChatColor.RED + "Group type " + ChatColor.YELLOW + typeName.str + ChatColor.RED + " does not exist. Use quotation marks (\" or ') when using spaces.");
            return;
        }
        String[] argsWithoutType = shortenArray(args, typeName.count);
        String groupName = String.join(" ", argsWithoutType);

        gm.addGroup(new Group(groupName, groupType));
        sender.sendMessage(ChatColor.GREEN + "Created a new " + ChatColor.YELLOW + typeName.str + ChatColor.GREEN + " named " + ChatColor.YELLOW + groupName + ChatColor.GREEN + ".");
    }

    private String[] shortenArray(String[] arr, int typeLength) {
        String[] result = new String[arr.length - typeLength];
        if (arr.length - typeLength >= 0)
            System.arraycopy(arr, typeLength, result, 0, arr.length - typeLength);
        return result;
    }

    private NameAndCount getTypeName(String[] args) {
        char openingQuote = '!';
        int wordCount = 0;
        if(args[0].charAt(0) == '\'' || args[0].charAt(0) == '"') {
            wordCount++;
            openingQuote = args[0].charAt(0);
        } else {
            return new NameAndCount(args[0], 1);
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
        if (closingQuote == '!' || (openingQuote != closingQuote)) return new NameAndCount(args[0], 1);

        String[] updatedArgs = nameWithoutQuotes(args, wordCount);
        return new NameAndCount(String.join(" ", updatedArgs), wordCount);
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
