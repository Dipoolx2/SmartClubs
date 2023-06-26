package smartclubs;

import smartclubs.command.management.CommandManager;
import smartclubs.data.DataManager;
import smartclubs.data.local.CacheLoader;
import smartclubs.group.management.GroupManager;
import smartclubs.profile.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmartClubs extends JavaPlugin {
    public static SmartClubs INSTANCE = null;
    public static String PERM_BASE = "smartclubs";

    private CommandManager commandManager;
    private CacheLoader cacheLoader;
    public GroupManager groupManager;
    public ProfileManager profileManager;
    public DataManager dataManager;

    public final boolean SINGLE_SERVER_MODE = true;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.getLogger().info("Initializing Command Manager");
        this.commandManager = new CommandManager(this);
        this.getLogger().info("Initializing Group Manager");
        this.groupManager = new GroupManager(this);
        this.getLogger().info("Initializing Profile Manager");
        this.profileManager = new ProfileManager(this);

        this.getLogger().info("Initializing Data Manager");
        this.dataManager = new DataManager(this, true);
        this.cacheLoader = new CacheLoader(dataManager, profileManager, groupManager);

        this.getLogger().info("Pre-filling cache");

        //initializeDataFromLocalStorage();
        this.getLogger().info("Enabled SmartClubs");
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }
}
