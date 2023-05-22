package smartclubs.group.management;

import smartclubs.SmartClubs;
import smartclubs.profile.PlayerProfile;

import java.util.HashMap;
import java.util.HashSet;

public class GroupManager {
    private SmartClubs pl;
    public HashMap<GroupType, HashSet<Group>> groups;
    private HashSet<GroupType> groupTypes;

    public GroupManager(SmartClubs plugin) {
        this.pl = plugin;
        this.groups = new HashMap<>();
        this.groupTypes = new HashSet<>();
    }

    public void addGroup(final Group group) {
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

    public void addGroupType(GroupType groupType) {
        this.groupTypes.add(groupType);
    }

    public boolean isGroupTypeNameTaken(String groupTypeName) {
        for (GroupType type : groupTypes) {
            if (type.groupTypeName.equalsIgnoreCase(groupTypeName)) return true;
        }
        return false;
    }

    // Case sensitive
    private Group getGroupFromName(String groupName, GroupType groupType) {
        if (!groups.containsKey(groupType)) {
            pl.getLogger().severe("Couldn't get group from name: There is no group with type " + groupType.groupTypeName + ".");
            return null;
        }
        if (groupName.isEmpty()) {
            pl.getLogger().severe("Couldn't get group from name: Group name is empty.");
            return null;
        }
        for (Group g : groups.get(groupType)) {
            if (g.compareName(groupName)) {
                return g;
            }
        }
        return null;
    }

    public boolean addProfileToGroup(String groupName, GroupType groupType, PlayerProfile profile) {
        Group groupToJoin = getGroupFromName(groupName, groupType);
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
        groupToJoin.addProfileToGroup(profile);
        return true;
    }

}
