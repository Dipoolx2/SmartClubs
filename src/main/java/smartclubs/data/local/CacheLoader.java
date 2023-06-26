package smartclubs.data.local;

import smartclubs.data.DataManager;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupManager;
import smartclubs.group.management.GroupType;
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

    public void writeCacheFromLocalStorage() {
        cacheProfiles();
        cacheGroupTypes();
        cacheGroups();
    }

    private void cacheProfiles() {
        List<PlayerProfile> profiles = new ArrayList<>(dm.getPlayerProfiles());
        for (PlayerProfile profile : profiles) {
            pm.registerProfileToCache(profile);
        }
    }

    private void cacheGroupTypes() {
        List<GroupType> groupTypes = new ArrayList<>(dm.getGroupTypes());
        for (GroupType groupType : groupTypes) {
            gm.registerGroupTypeToCache(groupType);
        }
    }

    private void cacheGroups() {
        List<Group> groups = new ArrayList<>(dm.getGroups());
        for (Group group : groups) {
            gm.registerGroupToCache(group);
        }
    }
}
