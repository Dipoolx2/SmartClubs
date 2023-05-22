package smartclubs.data.local.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupsDataManager {

    private YamlConfiguration groupsData;
    private File groupsFile;
    private final JavaPlugin pl;

    public GroupsDataManager(JavaPlugin pl) {
        this.pl = pl;
        createDataFile();
        initializeDataFile();
    }
    public void writeGroup(Group group) {
        GroupModel groupModel = new GroupModel(group);
        this.groupsData.set(groupModel.groupType.uniqueId+"."+groupModel.uniqueId +".name", groupModel.groupName);
        List<String> membersList = new ArrayList<>();
        groupModel.members.forEach(m -> membersList.add(m.uniqueId.toString()));
        this.groupsData.set(groupModel.groupType.uniqueId+"."+groupModel.uniqueId +".members", membersList);

        try {
            this.groupsData.save(groupsFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
