package smartclubs.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LocalDataManager {
    private JavaPlugin pl;
    private DataFileManager dataFileManager;

    private YamlConfiguration groupTypesData;
    private YamlConfiguration groupsData;
    private YamlConfiguration profilesData;

    public LocalDataManager(JavaPlugin pl) {
        this.pl = pl;
        this.dataFileManager = new DataFileManager(pl);
        initializeDataFiles();
    }

    private void initializeDataFiles() {
        this.groupTypesData = this.dataFileManager.groupTypesConfig;
        this.groupsData = this.dataFileManager.groupsConfig;
        this.profilesData = this.dataFileManager.profilesConfig;
    }
}
