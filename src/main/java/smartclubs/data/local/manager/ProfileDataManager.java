package smartclubs.data.local.manager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupData;
import smartclubs.profile.PlayerProfile;
import smartclubs.profile.ProfileData;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ProfileDataManager {
    private JavaPlugin pl;

    private YamlConfiguration profilesData = null;
    private File profilesFile = null;

    public ProfileDataManager(JavaPlugin pl) {
        this.pl = pl;
        if (createProfilesFile())
            initializeProfilesFile();
    }

    public List<PlayerProfile> getPlayerProfiles() {
        List<PlayerProfile> playerProfiles = new ArrayList<>();
        for (String profileUuid : Objects.requireNonNull(profilesData.getConfigurationSection("")).getKeys(false)) {
            playerProfiles.add(new PlayerProfile(UUID.fromString(profilesData.getString(profileUuid)+".profile-owner")));
        }
        return playerProfiles;
    }

    public PlayerProfile getPlayerProfile(UUID playerUuid) {
        if (pl.getServer().getOfflinePlayer(playerUuid).getFirstPlayed() == 0) {
            return null;
        }
        if (profilesData.isConfigurationSection(playerUuid.toString())) {
            return new PlayerProfile(playerUuid);
        }
        return null;
    }

    public boolean writeGroupToProfile(PlayerProfile profile, Group group) {
        ProfileData profileData = new ProfileData(profile);
        GroupData groupData = new GroupData(group);
        String listPath = profileData.profileOwnerUuid.toString()+".groups."+groupData.groupTypeUuid.toString();

        if (!profilesData.contains(profileData.profileOwnerUuid.toString())) {
            pl.getLogger().severe("Can't add group to profile in local data: Profile " + profileData.profileOwnerUuid.toString() +" isn't in local data.");
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
        profilesData.set(profileData.profileOwnerUuid.toString()+".profile-owner", profileData.profileOwnerUuid.toString());
        try {
            profilesData.save(profilesFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean createProfilesFile() {
        File dataFolder = new File(pl.getDataFolder(), "data");
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                pl.getLogger().severe("Couldn't create data folder. Aborting profiles data file creation.");
                return false;
            }
        }

        this.profilesFile = new File(dataFolder, "profiles.yml");
        if (!this.profilesFile.exists()) {
            pl.saveResource("data/profiles.yml", false);
        }
        this.profilesData = new YamlConfiguration();
        try {
            this.profilesData.load(profilesFile);
        } catch (Exception e) {
            pl.getLogger().severe("Something went wrong while initializing profiles.yml.");
        }
        return true;
    }

    private void initializeProfilesFile() {
        if (profilesData == null) {
            pl.getLogger().severe("Couldn't initialize profiles file: Expected file profiles.yml does not exist in plugin directory.");
            return;
        }
        this.profilesData = YamlConfiguration.loadConfiguration(this.profilesFile);
    }

}
