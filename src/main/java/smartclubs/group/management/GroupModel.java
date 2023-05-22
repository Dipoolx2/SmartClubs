package smartclubs.group.management;

import smartclubs.profile.PlayerProfile;

import java.util.HashSet;
import java.util.UUID;

public class GroupModel {
    public final UUID uniqueId;
    public String groupName;
    public HashSet<PlayerProfile> members;
    public GroupType groupType;

    public GroupModel(Group group) {
        this.uniqueId = group.uniqueId;
        this.groupName = group.groupName;
        this.members = new HashSet<>();
        this.members.addAll(group.members);
        this.groupType = group.groupType;
    }
}
