package smartclubs.command.commands;

import smartclubs.command.commands.group.GroupCreate;

public class CommandUtils {
    public static String[] shortenArray(String[] arr, int typeLength) {
        String[] result = new String[arr.length - typeLength];
        if (arr.length - typeLength >= 0)
            System.arraycopy(arr, typeLength, result, 0, arr.length - typeLength);
        return result;
    }

    public static CommandUtils.NameAndCount getTypeName(String[] args) {
        char openingQuote = '!';
        int wordCount = 0;
        if(args[0].charAt(0) == '\'' || args[0].charAt(0) == '"') {
            wordCount++;
            openingQuote = args[0].charAt(0);
        } else {
            return new CommandUtils.NameAndCount(args[0], 1);
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
        if (closingQuote == '!' || (openingQuote != closingQuote)) return new CommandUtils.NameAndCount(args[0], 1);

        String[] updatedArgs = nameWithoutQuotes(args, wordCount);
        return new CommandUtils.NameAndCount(String.join(" ", updatedArgs), wordCount);
    }

    private static String[] nameWithoutQuotes(String[] args, int wordCount) {
        if (args.length == 0) return new String[0];
        String[] result = new String[wordCount];
        System.arraycopy(args, 0, result, 0, wordCount);
        result[0] = result[0].substring(1);
        result[wordCount - 1] = result[wordCount - 1].substring(0, result[wordCount - 1].length()-1);
        return result;
    }

    public static class NameAndCount {
        public String str;
        public int count;
        private NameAndCount(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
