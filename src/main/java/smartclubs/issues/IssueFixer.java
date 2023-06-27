package smartclubs.issues;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class IssueFixer {

    public ArrayList<Issue> issues;

    public IssueFixer() {
        this.issues = new ArrayList<>();
    }

    public boolean fixIssues() {
        boolean fixedAll = true;
        for (Issue issue : issues) {
            if (!issue.issueFix.fix()) {
                fixedAll = false;
            }
        }
        return fixedAll;
    }

}
