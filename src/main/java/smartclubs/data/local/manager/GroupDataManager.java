package smartclubs.data.local.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class GroupDataManager {
    private JavaPlugin pl;

    private YamlConfiguration groupsData = null;
    private File groupsFile = null;

    public GroupDataManager(JavaPlugin pl) {
        this.pl = pl;
        if (createGroupsFile())
            initializeGroupsFile();
    }

    public boolean createGroupsFile() {
        File dataFolder = new File(pl.getDataFolder(), "data");
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                pl.getLogger().severe("Couldn't create data folder. Aborting groups data file creation.");
                return false;
            }
        }

        this.groupsFile = new File(dataFolder, "groups.yml");
        if (!this.groupsFile.exists()) {
            pl.saveResource("data/groups.yml", false);
            this.groupsData = new YamlConfiguration();
            try {
                this.groupsData.load(groupsFile);
            } catch (Exception e) {
                pl.getLogger().severe("Something went wrong while initializing groups.yml.");
            }
        }
        return true;
    }

    public void initializeGroupsFile() {
        if (groupsData == null) {
            pl.getLogger().severe("Couldn't initialize groups file: Expected file groups.yml does not exist in plugin directory.");
            return;
        }
        this.groupsData = YamlConfiguration.loadConfiguration(this.groupsFile);
    }

}
