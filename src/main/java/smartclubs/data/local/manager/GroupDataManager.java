package smartclubs.data.local.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupData;
import smartclubs.profile.PlayerProfile;
import smartclubs.profile.ProfileData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GroupDataManager {
    private JavaPlugin pl;

    private YamlConfiguration groupsData = null;
    private File groupsFile = null;

    public GroupDataManager(JavaPlugin pl) {
        this.pl = pl;
        if (createGroupsFile())
            initializeGroupsFile();
    }

    public List<Group> getGroups() {
        List<Group> result = new ArrayList<>();
        for (String groupTypeUuid : Objects.requireNonNull(groupsData.getConfigurationSection("")).getKeys(false)) {
            for (String groupUuid : Objects.requireNonNull(groupsData.getConfigurationSection(groupTypeUuid)).getKeys(false)) {
                String groupName = groupsData.getString(groupTypeUuid+"."+groupUuid+".name");
                List<UUID> members = new ArrayList<>();
                groupsData.getStringList(groupTypeUuid+"."+groupUuid+".members").forEach(s -> members.add(UUID.fromString(s)));
                Group newGroup = new Group(UUID.fromString(groupUuid), UUID.fromString(groupTypeUuid),groupName, members);
                result.add(newGroup);
            }
        }
        return result;
    }

    public void writeGroup(Group group) {
        GroupData groupData = new GroupData(group);
        this.groupsData.set(groupData.groupTypeUuid+"."+ groupData.uniqueId +".name", groupData.groupName);
        List<UUID> membersList = new ArrayList<>(groupData.members);
        this.groupsData.set(groupData.groupTypeUuid+"."+ groupData.uniqueId +".members", membersList);

        try {
            this.groupsData.save(groupsFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean writeProfileToGroupMembers(Group group, PlayerProfile profile) {
        GroupData groupData = new GroupData(group);
        ProfileData profileData = new ProfileData(profile);
        String listPath = groupData.groupTypeUuid.toString()+"."+groupData.uniqueId.toString()+".members";
        if (!groupsData.contains(groupData.groupTypeUuid.toString()+"."+groupData.uniqueId)) {
            pl.getLogger().severe("Couldn't add profile to group members in local storage: Group " + groupData.uniqueId.toString() + " does not exist.");
            return false;
        }
        if (!groupsData.contains(listPath)) {
            groupsData.set(listPath, new ArrayList<String>());
        }
        List<String> stringList = groupsData.getStringList(listPath);
        if (!stringList.contains(profileData.profileOwnerUuid.toString())) {
            stringList.add(profileData.profileOwnerUuid.toString());
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
