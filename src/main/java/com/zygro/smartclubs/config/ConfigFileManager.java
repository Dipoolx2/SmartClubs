package com.zygro.smartclubs.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigFileManager {
    private JavaPlugin pl;

    private YamlConfiguration groupTypesConfig;
    private YamlConfiguration groupsConfig;
    private YamlConfiguration profilesConfig;

    public ConfigFileManager(JavaPlugin pl) {
        this.pl = pl;

        createDataFiles();
        initializeConfigurationFiles();
    }

    public void initializeConfigurationFiles() {
        // Initialize group types file
        File group_types_file = new File(pl.getDataFolder(), "group-types.yml");
        this.groupTypesConfig = YamlConfiguration.loadConfiguration(group_types_file);

        // Initialize groups file
        File groups_file = new File(pl.getDataFolder(), "groups.yml");
        this.groupsConfig = YamlConfiguration.loadConfiguration(groups_file);

        // Initialize profiles file
        File profiles_file = new File(pl.getDataFolder(), "profiles.yml");
        this.profilesConfig = YamlConfiguration.loadConfiguration(profiles_file);

    }

    private void createDataFiles() {
        File profilesFile = new File(pl.getDataFolder(), "profiles.yml");
        File groupTypesFile = new File(pl.getDataFolder(), "group-types.yml");
        File groupsFile = new File(pl.getDataFolder(), "groups.yml");
        if (!profilesFile.exists()) {
            pl.saveResource("profiles.yml", false);
        }
        if (!groupTypesFile.exists()) {
            pl.saveResource("group-types.yml", false);
        }
        if (!groupsFile.exists()) {
            pl.saveResource("groups.yml", false);
        }
    }

    public YamlConfiguration getGroupsConfig() {
        return groupsConfig;
    }

    public YamlConfiguration getGroupTypesConfig() {
        return groupTypesConfig;
    }

    public YamlConfiguration getProfilesConfig() {
        return profilesConfig;
    }
}
