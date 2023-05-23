package smartclubs.data.local.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.group.management.GroupType;
import smartclubs.group.management.GroupTypeData;

import java.io.File;
import java.io.IOException;

public class GroupTypesDataManager {

    private YamlConfiguration groupTypesData;
    private File groupTypesFile;
    private final JavaPlugin pl;

    public GroupTypesDataManager(JavaPlugin pl) {
        this.pl = pl;
        createDataFile();
        initializeDataFile();
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
