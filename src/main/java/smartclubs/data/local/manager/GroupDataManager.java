package smartclubs.data.local.manager;

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

public class GroupDataManager {
    private JavaPlugin pl;

    private YamlConfiguration groupsData = null;
    private File groupsFile = null;

    public GroupDataManager(JavaPlugin pl) {
        this.pl = pl;
        if (createGroupsFile())
            initializeGroupsFile();
    }

    public void writeGroup(Group group) {
        GroupData groupData = new GroupData(group);
        this.groupsData.set(groupData.groupType.uniqueId+"."+ groupData.uniqueId +".name", groupData.groupName);
        List<String> membersList = new ArrayList<>();
        groupData.members.forEach(m -> membersList.add(m.profileOwner.getUniqueId().toString()));
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
        if (!stringList.contains(profileData.profileOwner.getUniqueId().toString())) {
            stringList.add(profileData.profileOwner.getUniqueId().toString());
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

    private boolean createGroupsFile() {
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
        }
        this.groupsData = new YamlConfiguration();
        try {
            this.groupsData.load(groupsFile);
        } catch (Exception e) {
            pl.getLogger().severe("Something went wrong while initializing groups.yml.");
        }
        return true;
    }

    private void initializeGroupsFile() {
        if (groupsData == null) {
            pl.getLogger().severe("Couldn't initialize groups file: Expected file groups.yml does not exist in plugin directory.");
            return;
        }
        this.groupsData = YamlConfiguration.loadConfiguration(this.groupsFile);
    }

}