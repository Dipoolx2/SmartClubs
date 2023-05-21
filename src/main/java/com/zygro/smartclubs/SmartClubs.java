package com.zygro.smartclubs;

import com.zygro.smartclubs.command.management.CommandManager;
import com.zygro.smartclubs.config.ConfigurationManager;
import com.zygro.smartclubs.group.management.GroupManager;
import com.zygro.smartclubs.profile.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmartClubs extends JavaPlugin {
    public static SmartClubs INSTANCE = null;
    public static String PERM_BASE = "smartclubs";
    private CommandManager commandManager;
    public GroupManager groupManager;
    public ProfileManager profileManager;
    public ConfigurationManager configurationManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.getLogger().info("Initializing Command Manager");
        this.commandManager = new CommandManager(this);
        this.getLogger().info("Initializing Group Manager");
        this.groupManager = new GroupManager(this);
        this.getLogger().info("Initializing Profile Manager");
        this.profileManager = new ProfileManager(this);

        this.getLogger().info("Initializing Configuration Manager");
        this.configurationManager = new ConfigurationManager(this);
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }
}
