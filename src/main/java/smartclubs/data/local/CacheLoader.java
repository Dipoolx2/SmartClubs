package smartclubs.data.local;

import smartclubs.data.DataManager;
import smartclubs.group.management.GroupManager;
import smartclubs.profile.PlayerProfile;
import smartclubs.profile.ProfileManager;

import java.util.ArrayList;
import java.util.List;

public class CacheLoader {

    private ProfileManager pm;
    private GroupManager gm;
    private DataManager dm;

    public CacheLoader(DataManager dm, ProfileManager pm, GroupManager gm) {
        this.pm = pm;
        this.gm = gm;
        this.dm = dm;
    }

    private void writeCacheFromLocalStorage() {
        cacheProfiles();
//        initializeGroupTypes();
//        initializeGroups();
//
//        linkProfilesToGroups();
//        linkGroupsToProfiles();
    }

    private void cacheProfiles() {
        List<PlayerProfile> profiles = new ArrayList<PlayerProfile>();
        profiles.addAll(dm.getPlayerProfiles());
        for (PlayerProfile profile : profiles) {
            pm.registerProfileToCache(profile);
        }
    }
}
