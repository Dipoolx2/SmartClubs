package smartclubs.data.local.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupData;
import smartclubs.profile.PlayerProfile;
import smartclubs.profile.ProfileData;

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
        GroupData groupData = new GroupData(group);
        this.groupsData.set(groupData.groupType.uniqueId+"."+ groupData.uniqueId +".name", groupData.groupName);
        List<String> membersList = new ArrayList<>();
        groupData.members.forEach(m -> membersList.add(m.uniqueId.toString()));
        this.groupsData.set(groupData.groupType.uniqueId+"."+ groupData.uniqueId +".members", membersList);

        try {
            this.groupsData.save(groupsFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean writeProfileToGroupMembers(Group group, PlayerProfile profile) {
        GroupData groupData = new GroupData(group);
        ProfileData profileData = new ProfileData(profile);
        String listPath = groupData.groupType.uniqueId.toString()+"."+groupData.uniqueId.toString()+".members";
        if (!groupsData.contains(groupData.groupType.uniqueId.toString()+"."+groupData.uniqueId)) {
            pl.getLogger().severe("Couldn't add profile to group members in local storage: Group " + groupData.uniqueId.toString() + " does not exist.");
            return false;
        }
        if (!groupsData.contains(listPath)) {
            groupsData.set(listPath, new ArrayList<String>());
        }
        List<String> stringList = groupsData.getStringList(listPath);
        if (!stringList.contains(profileData.uniqueId.toString())) {
            stringList.add(profileData.uniqueId.toString());
        } else {
            return false;
        }
        groupsData.set(listPath, stringList);
        try {
            groupsData.save(groupsFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
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
