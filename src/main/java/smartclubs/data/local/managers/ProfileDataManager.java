package smartclubs.data.local.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.profile.ProfileModel;

import java.io.File;
import java.io.IOException;

public class ProfileDataManager {

    private YamlConfiguration profilesData;
    private File profilesFile;
    private final JavaPlugin pl;

    public ProfileDataManager(JavaPlugin pl) {
        this.pl = pl;
        createDataFile();
        initializeDataFile();
    }

    public void writeProfile(ProfileModel profile) {
        profilesData.set(profile.uniqueId.toString()+".profile-owner", profile.profileOwner.getUniqueId().toString());
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
