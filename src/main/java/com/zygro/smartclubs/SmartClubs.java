package com.zygro.smartclubs;

import com.zygro.smartclubs.command.management.CommandManager;
import com.zygro.smartclubs.group.management.GroupManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmartClubs extends JavaPlugin {
    public static SmartClubs INSTANCE = null;
    public static String PERM_BASE = "smartclubs";
    private CommandManager commandManager;
    public GroupManager groupManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.getLogger().info("Initializing Command Manager");
        this.commandManager = new CommandManager(this);
        this.groupManager = new GroupManager(this);
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }
}
