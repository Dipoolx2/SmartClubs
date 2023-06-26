package smartclubs.data.local.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.group.management.GroupType;
import smartclubs.group.management.GroupTypeData;

import java.io.File;
import java.io.IOException;

public class GroupTypeDataManager {
    private JavaPlugin pl;

    private YamlConfiguration groupTypesData = null;

    private File groupTypesFile = null;

    public GroupTypeDataManager(JavaPlugin pl) {
        this.pl = pl;
        if (createGroupTypesFile())
            initializeGroupTypesFile();
    }

    public void writeGroupType(GroupType groupType) {
        GroupTypeData groupTypeData = new GroupTypeData(groupType);
        this.groupTypesData.set(groupTypeData.uniqueId+".name", groupTypeData.groupTypeName);

        try {
            this.groupTypesData.save(groupTypesFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean createGroupTypesFile() {
        File dataFolder = new File(pl.getDataFolder(), "data");
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                pl.getLogger().severe("Couldn't create data folder. Aborting group types data file creation.");
                return false;
            }
        }

        this.groupTypesFile = new File(dataFolder, "group-types.yml");
        if (!this.groupTypesFile.exists()) {
            pl.saveResource("data/group-types.yml", false);
            this.groupTypesData = new YamlConfiguration();
            try {
                this.groupTypesData.load(groupTypesFile);
            } catch (Exception e) {
                pl.getLogger().severe("Something went wrong while initializing group-types.yml.");
            }
        }
        return true;
    }

    public void initializeGroupTypesFile() {
        if (groupTypesData == null) {
            pl.getLogger().severe("Couldn't initialize group types file: Expected file group-types.yml does not exist in plugin directory.");
            return;
        }
        this.groupTypesData = YamlConfiguration.loadConfiguration(this.groupTypesFile);
    }
}
