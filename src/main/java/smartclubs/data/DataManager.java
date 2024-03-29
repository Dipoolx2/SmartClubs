package smartclubs.data;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import smartclubs.SmartClubs;
import smartclubs.data.local.LocalDataManager;
import smartclubs.data.local.manager.ProfileDataManager;
import smartclubs.group.management.Group;
import smartclubs.group.management.GroupType;
import smartclubs.profile.PlayerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DataManager {
    private SmartClubs pl;

    private final boolean USE_LOCAL_DATA;

    private LocalDataManager localDataManager;

    public DataManager(SmartClubs pl, boolean useLocalData) {
        this.pl = pl;
        this.USE_LOCAL_DATA = useLocalData;
        if (USE_LOCAL_DATA) {
            this.localDataManager = new LocalDataManager(pl);
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!this.isProfileInData(player.getUniqueId()))
                this.addProfileToData(new PlayerProfile(player.getUniqueId()));

        }

    }

    // --- GROUP TYPE DATA ---
    public void addGroupTypeToData(GroupType groupType) {
        if (USE_LOCAL_DATA) {
            localDataManager.groupTypesData.writeGroupType(groupType);
        }
    }

    public boolean isGroupTypeNameTaken(String groupTypeName) {
        if (pl.SINGLE_SERVER_MODE) {
            return pl.groupManager.isGroupTypeNameTakenInCache(groupTypeName);
        }
        return false;
    }

    public List<GroupType> getGroupTypes() {
        if (USE_LOCAL_DATA) {
            return localDataManager.groupTypesData.getGroupTypes();
        }

        return null;
    }

    // --- GROUP DATA ---
    public void addGroupToData(Group group) {
        if (USE_LOCAL_DATA) {
            localDataManager.groupsData.writeGroup(group);
        }
    }

    public boolean addProfileToGroupMembersData(Group group, PlayerProfile profile) {
        if (USE_LOCAL_DATA) {
            return localDataManager.groupsData.writeProfileToGroupMembers(group, profile);
        }
        return false;
    }

    public boolean isGroupNameTaken(GroupType groupType, String groupName) {
        if (pl.SINGLE_SERVER_MODE) {
            return pl.groupManager.isGroupNameTakenInCache(groupType, groupName);
        }
        return false;
    }

    public List<Group> getGroups() {
        if (USE_LOCAL_DATA) {
            return localDataManager.groupsData.getGroups();
        }
        return null;
    }

    // Returns null if there's an error reading.
    public Group getGroupData(UUID groupTypeUuid, UUID uuid) {
        if (USE_LOCAL_DATA) {
            return localDataManager.groupsData.getGroupData(groupTypeUuid, uuid);
        }
        return null;
    }

    // --- PROFILE DATA ---
    public void addProfileToData(PlayerProfile profile) {
        if (USE_LOCAL_DATA) {
            localDataManager.profileData.writeProfile(profile);
        }
    }

    public boolean addGroupToProfileData(PlayerProfile profile, Group group) {
        if (USE_LOCAL_DATA) {
            return localDataManager.profileData.writeGroupToProfile(profile, group);
        }
        return false;
    }

    public PlayerProfile getPlayerProfile(OfflinePlayer player) {
        if (pl.profileManager.getPlayerProfileFromCache(player) != null) {
            return pl.profileManager.getPlayerProfileFromCache(player);
        }
        if (USE_LOCAL_DATA) {
            return localDataManager.profileData.getPlayerProfile(player.getUniqueId());
        }
        return null;
    }

    public List<UUID> getPlayerGroupsByType(UUID playerUuid, UUID groupTypeUuid) {
        if (USE_LOCAL_DATA) {
            return localDataManager.profileData.getPlayerGroupsByType(playerUuid, groupTypeUuid);
        }
        return null;
    }

    public List<PlayerProfile> getPlayerProfiles() {
        if (USE_LOCAL_DATA) {
            return localDataManager.profileData.getPlayerProfiles();
        }
        return null;
    }

    public boolean isProfileInData(UUID uuid) {
        if (USE_LOCAL_DATA) {
            return localDataManager.profileData.getPlayerProfile(uuid) != null;
        }

        return false;
    }

    public ProfileDataManager getProfileDataManager() {
        return this.localDataManager.profileData;
    }

}
