package smartclubs.group.management;

import smartclubs.SmartClubs;
import smartclubs.profile.PlayerProfile;
import smartclubs.profile.ProfileData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class GroupManager {
    private SmartClubs pl;
    private HashMap<UUID, HashSet<Group>> groups; // Key = group type uuid
    private HashSet<GroupType> groupTypes;

    public GroupManager(SmartClubs plugin) {
        this.pl = plugin;
        this.groups = new HashMap<>();
        this.groupTypes = new HashSet<>();
    }

    public void registerGroupToCache(final Group group) {
        if (!groups.containsKey(group.groupType)) {
            groups.put(group.groupType, new HashSet<>());
        }
        groups.get(group.groupType).add(group);
    }

    public GroupType getGroupTypeFromName(String groupTypeName) {
        for (GroupType groupType : this.groupTypes) {
            if (groupType.groupTypeName.equalsIgnoreCase(groupTypeName)) {
                return groupType;
            }
        }
        return null;
    }

    public void registerGroupTypeToCache(GroupType groupType) {
        this.groupTypes.add(groupType);
    }

    public boolean isGroupTypeNameTakenInCache(String groupTypeName) {
        for (GroupType type : groupTypes) {
            if (type.groupTypeName.equalsIgnoreCase(groupTypeName)) return true;
        }
        return false;
    }

    public boolean isGroupNameTakenInCache(GroupType groupType, String groupName) {
        GroupTypeData groupTypeData = new GroupTypeData(groupType);
        if (!groups.containsKey(groupTypeData.uniqueId)) {
            return false;
        }
        for (Group group : groups.get(groupTypeData.uniqueId)) {
            if (group.compareName(groupName)) return true;
        }
        return false;
    }

    // Case sensitive
    private Group getGroupFromName(String groupName, GroupType groupType) {
        GroupTypeData groupTypeData = new GroupTypeData(groupType);
        if (!groups.containsKey(groupTypeData.uniqueId)) {
            pl.getLogger().severe("Couldn't get group from name: There is no group with type " + groupType.groupTypeName + ".");
            return null;
        }
        if (groupName.isEmpty()) {
            pl.getLogger().severe("Couldn't get group from name: Group name is empty.");
            return null;
        }
        for (Group g : groups.get(groupTypeData.uniqueId)) {
            if (g.compareName(groupName)) {
                return g;
            }
        }
        return null;
    }

    private boolean addUserToGroupInRam(Group groupToJoin, PlayerProfile profile) {
        ProfileData profileData = new ProfileData(profile);
        if (groupToJoin == null) {
            pl.getLogger().severe("Couldn't add player to group: Group doesn't exist.");
            return false;
        }
        if (!groups.containsKey(groupToJoin.groupType)) {
            pl.getLogger().severe("Couldn't add player to group: Group type doesn't exist.");
            return false;
        }
        if (!groups.get(groupToJoin.groupType).contains(groupToJoin)) {
            pl.getLogger().severe("Couldn't add player to group: Group doesn't exist.");
            return false;
        }
        if (groupToJoin.members.contains(profileData.profileOwnerUuid)) {
            return false;
        }
        groupToJoin.addProfileToGroup(profileData.profileOwnerUuid);
        return true;
    }

    public boolean addProfileToGroup(String groupName, GroupType groupType, PlayerProfile profile) {
        Group groupToJoin = getGroupFromName(groupName, groupType);
        if (!addUserToGroupInRam(groupToJoin, profile)) return false;
        return !(!SmartClubs.INSTANCE.dataManager.addGroupToProfileData(profile, groupToJoin) ||
                !SmartClubs.INSTANCE.dataManager.addProfileToGroupMembersData(groupToJoin, profile));
    }

}
