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

public class ProfileDataManager {

    private YamlConfiguration profilesData;
    private File profilesFile;
    private final JavaPlugin pl;

    public ProfileDataManager(JavaPlugin pl) {
        this.pl = pl;
        createDataFile();
        initializeDataFile();
    }

    public boolean writeGroupToProfile(PlayerProfile profile, Group group) {
        ProfileData profileData = new ProfileData(profile);
        GroupData groupData = new GroupData(group);
        String listPath = profileData.uniqueId.toString()+".groups."+groupData.groupType.uniqueId;

        if (!profilesData.contains(profileData.uniqueId.toString())) {
            pl.getLogger().severe("Can't add group to profile in local data: Profile " + profileData.uniqueId.toString() +" isn't in local data.");
            return false;
        }
        if (!profilesData.contains(listPath)) {
            profilesData.set(listPath, new ArrayList<String>());
        }
        List<String> groupList = profilesData.getStringList(listPath);
        if (!groupList.contains(groupData.uniqueId.toString())) {
            groupList.add(groupData.uniqueId.toString());
        } else {
            return false;
        }
        profilesData.set(listPath, groupList);
        try {
            profilesData.save(profilesFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void writeProfile(PlayerProfile profile) {
        ProfileData profileData = new ProfileData(profile);
        profilesData.set(profileData.uniqueId.toString()+".profile-owner", profileData.profileOwner.getUniqueId().toString());
        try {
            profilesData.save(profilesFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initializeDataFile() {
        assert this.profilesFile != null;
        this.profilesData = YamlConfiguration.loadConfiguration(this.profilesFile);
    }

    private void createDataFile() {
        this.profilesFile = new File(pl.getDataFolder(), "profiles.yml");
        if (!profilesFile.exists()) {
            pl.saveResource("profiles.yml", false);
        }
    }
}
