package smartclubs;

import smartclubs.command.management.CommandManager;
import smartclubs.config.LocalDataManager;
import smartclubs.group.management.GroupManager;
import smartclubs.profile.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmartClubs extends JavaPlugin {
    public static SmartClubs INSTANCE = null;
    public static String PERM_BASE = "smartclubs";
    private CommandManager commandManager;
    public GroupManager groupManager;
    public ProfileManager profileManager;
    public LocalDataManager localDataManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.getLogger().info("Initializing Command Manager");
        this.commandManager = new CommandManager(this);
        this.getLogger().info("Initializing Group Manager");
        this.groupManager = new GroupManager(this);
        this.getLogger().info("Initializing Profile Manager");
        this.profileManager = new ProfileManager(this);

        this.getLogger().info("Initializing Local Data Manager");
        this.localDataManager = new LocalDataManager(this);
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }
}
