package smartclubs;

import smartclubs.command.management.CommandManager;
import smartclubs.data.DataManager;
import smartclubs.data.local.CacheLoader;
import smartclubs.group.management.GroupManager;
import smartclubs.issues.IssueFixer;
import smartclubs.issues.impl.localdata.UnexistingGroupTypeInMember;
import smartclubs.profile.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public final class SmartClubs extends JavaPlugin {
    public static SmartClubs INSTANCE = null;
    public static String PERM_BASE = "smartclubs";

    public IssueFixer issueFixer;
    private CommandManager commandManager;
    private CacheLoader cacheLoader;
    public GroupManager groupManager;
    public ProfileManager profileManager;
    public DataManager dataManager;

    public final boolean SINGLE_SERVER_MODE = true;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.issueFixer = new IssueFixer();
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
        this.cacheLoader.writeCacheFromLocalStorage();
        //initializeDataFromLocalStorage();
        this.getLogger().info("Enabled SmartClubs");

        this.issueFixer.issues.add(new UnexistingGroupTypeInMember(UUID.fromString("f00c264b-f64a-4d6b-9ad6-f1b584f7407c"), UUID.fromString("e55cbf3a-dd14-4640-ac12-c9cfa70fa710")));
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }
}
