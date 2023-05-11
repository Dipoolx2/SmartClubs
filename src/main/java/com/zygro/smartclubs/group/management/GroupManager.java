package com.zygro.smartclubs.group.management;

import com.zygro.smartclubs.SmartClubs;

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

}
