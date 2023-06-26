package smartclubs.data.local;

import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.data.local.manager.GroupDataManager;
import smartclubs.data.local.manager.GroupTypeDataManager;
import smartclubs.data.local.manager.ProfileDataManager;

public class LocalDataManager {
    private JavaPlugin pl;
    public ProfileDataManager profileData;
    public GroupDataManager groupsData;
    public GroupTypeDataManager groupTypesData;

    public LocalDataManager(JavaPlugin pl) {
        this.pl = pl;
        this.groupTypesData = new GroupTypeDataManager(pl);
        this.groupsData = new GroupDataManager(pl);
        this.profileData = new ProfileDataManager(pl);
    }
}
