package smartclubs.data.local.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ProfileDataManager {
    private JavaPlugin pl;

    private YamlConfiguration profilesData = null;
    private File profilesFile = null;

    public ProfileDataManager(JavaPlugin pl) {
        this.pl = pl;
        if (createProfilesFile())
            initializeProfilesFile();
    }

    public boolean createProfilesFile() {
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
            this.profilesData = new YamlConfiguration();
            try {
                this.profilesData.load(profilesFile);
            } catch (Exception e) {
                pl.getLogger().severe("Something went wrong while initializing profiles.yml.");
            }
        }
        return true;
    }

    public void initializeProfilesFile() {
        if (profilesData == null) {
            pl.getLogger().severe("Couldn't initialize profiles file: Expected file profiles.yml does not exist in plugin directory.");
            return;
        }
        this.profilesData = YamlConfiguration.loadConfiguration(this.profilesFile);
    }

}
