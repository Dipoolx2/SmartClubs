package smartclubs.data.local;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.data.local.managers.GroupTypesDataManager;
import smartclubs.data.local.managers.GroupsDataManager;
import smartclubs.data.local.managers.ProfileDataManager;

public class LocalDataManager {
    private JavaPlugin pl;
    public ProfileDataManager profileData;
    public GroupsDataManager groupsData;
    public GroupTypesDataManager groupTypesData;

    protected YamlConfiguration groupTypesConfig;
    protected YamlConfiguration groupsConfig;
    public LocalDataManager(JavaPlugin pl) {
        this.pl = pl;
        this.groupTypesData = new GroupTypesDataManager(pl);
        this.groupsData = new GroupsDataManager(pl);
        this.profileData = new ProfileDataManager(pl);
    }
}
