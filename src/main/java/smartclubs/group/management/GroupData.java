package smartclubs.group.management;

import smartclubs.profile.ProfileData;

import java.util.HashSet;
import java.util.UUID;

public class GroupData {
    public final UUID uniqueId;
    public String groupName;
    public HashSet<ProfileData> members;
    public GroupTypeData groupType;

    public GroupData(Group group) {
        this.uniqueId = group.uniqueId;
        this.groupName = group.groupName;
        this.members = new HashSet<>();
        group.members.forEach(m -> {
            members.add(new ProfileData(m));
        });
        this.groupType = new GroupTypeData(group.groupType);
    }
}
