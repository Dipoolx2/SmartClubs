package smartclubs.data.local;

import org.bukkit.plugin.java.JavaPlugin;
import smartclubs.data.local.manager.GroupDataManager;
import smartclubs.data.local.manager.GroupTypeDataManager;
import smartclubs.data.local.manager.ProfileDataManager;

public class LocalDataManager {
    public GroupDataManager groupData;
    public GroupTypeDataManager groupTypeData;
    public ProfileDataManager profileData;

    public LocalDataManager(JavaPlugin pl) {
        this.groupData = new GroupDataManager(pl);
        this.groupTypeData = new GroupTypeDataManager(pl);
        this.profileData = new ProfileDataManager(pl);
    }
}
