package smartclubs.data.local;

import smartclubs.group.management.GroupManager;
import smartclubs.profile.PlayerProfile;
import smartclubs.profile.ProfileManager;

import java.util.ArrayList;
import java.util.List;

public class CacheLoader {

    private ProfileManager pm;
    private GroupManager gm;
    private LocalDataManager ldm;

    public CacheLoader(LocalDataManager ldm, ProfileManager pm, GroupManager gm) {
        this.pm = pm;
        this.gm = gm;
        this.ldm = ldm;
    }

    private void initializeDataFromLocalStorage() {
        initializeProfiles();
        initializeGroupTypes();
        initializeGroups();

        linkProfilesToGroups();
        linkGroupsToProfiles();
    }

    private void initializeProfiles() {
        List<PlayerProfile> profiles = new ArrayList<PlayerProfile>();
        profiles.addAll(ldm.profileData.getProfiles());
        for (PlayerProfile profile : profiles) {
            pm.registerProfile(profile);
        }
    }
}
