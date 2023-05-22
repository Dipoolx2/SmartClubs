package smartclubs.data.local;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.profile.PlayerProfile;
import smartclubs.profile.ProfileModel;

import java.util.UUID;

public class LocalDataManager {
    private JavaPlugin pl;
    private DataFileManager dataFileManager;

    private YamlConfiguration groupTypesData;
    private YamlConfiguration groupsData;
    private YamlConfiguration profilesData;

    public LocalDataManager(JavaPlugin pl) {
        this.pl = pl;
        this.dataFileManager = new DataFileManager(pl);
        initializeDataFiles();
    }

    public void writeProfile(ProfileModel profile) {
        profilesData.set(profile.uniqueId.toString()+".profile-owner", profile.profileOwner.getUniqueId().toString());
        profilesData.save(file);
        // TODO: MAKE SEPARATE CLASSES FOR EACH FILE, ADD TO DIRECTORY
    }

    private void initializeDataFiles() {
        this.groupTypesData = this.dataFileManager.groupTypesConfig;
        this.groupsData = this.dataFileManager.groupsConfig;
        this.profilesData = this.dataFileManager.profilesConfig;
    }
}
