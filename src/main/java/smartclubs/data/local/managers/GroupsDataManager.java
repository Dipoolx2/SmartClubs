package smartclubs.data.local.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class GroupsDataManager {

    private YamlConfiguration groupsData;
    private File groupsFile;
    private final JavaPlugin pl;

    public GroupsDataManager(JavaPlugin pl) {
        this.pl = pl;
        createDataFile();
        initializeDataFile();
    }
    private void initializeDataFile() {
        assert this.groupsFile != null;
        this.groupsData = YamlConfiguration.loadConfiguration(this.groupsFile);
    }

    private void createDataFile() {
        this.groupsFile = new File(pl.getDataFolder(), "groups.yml");
        if (!groupsFile.exists()) {
            pl.saveResource("groups.yml", false);
        }
    }
}
