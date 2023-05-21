package com.zygro.smartclubs.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DataFileManager {
    private JavaPlugin pl;

    protected YamlConfiguration groupTypesConfig;
    protected YamlConfiguration groupsConfig;
    protected YamlConfiguration profilesConfig;

    public DataFileManager(JavaPlugin pl) {
        this.pl = pl;

        createDataFiles();
        initializeDataFiles();
    }

    private void initializeDataFiles() {
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
}
