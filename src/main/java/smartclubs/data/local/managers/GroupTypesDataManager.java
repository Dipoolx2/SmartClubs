package smartclubs.data.local.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class GroupTypesDataManager {

    private YamlConfiguration groupTypesData;
    private File groupTypesFile;
    private final JavaPlugin pl;

    public GroupTypesDataManager(JavaPlugin pl) {
        this.pl = pl;
        createDataFile();
        initializeDataFile();
    }
    private void initializeDataFile() {
        assert this.groupTypesData != null;
        this.groupTypesData = YamlConfiguration.loadConfiguration(this.groupTypesFile);
    }

    private void createDataFile() {
        this.groupTypesFile = new File(pl.getDataFolder(), "group-types.yml");
        if (!groupTypesFile.exists()) {
            pl.saveResource("group-types.yml", false);
        }
    }
}
