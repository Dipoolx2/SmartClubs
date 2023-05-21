package com.zygro.smartclubs.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigurationManager {
    private JavaPlugin pl;
    private ConfigFileManager configFileManager;

    private YamlConfiguration groupTypesConfig;
    private YamlConfiguration groupsConfig;
    private YamlConfiguration profilesConfig;

    public ConfigurationManager(JavaPlugin pl) {
        this.pl = pl;
        this.configFileManager = new ConfigFileManager(pl);
        initializeConfigFiles();
    }

    private void initializeConfigFiles() {
        this.groupTypesConfig = this.configFileManager.getGroupTypesConfig();
        this.groupsConfig = this.configFileManager.getGroupsConfig();
        this.profilesConfig = this.configFileManager.getProfilesConfig();
    }
}
