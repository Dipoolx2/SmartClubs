package smartclubs.group.management;

import org.bukkit.Bukkit;
import smartclubs.profile.ProfileData;

import java.util.HashSet;
import java.util.UUID;

public class GroupData {
    public final UUID uniqueId;
    public String groupName;
    public HashSet<UUID> members;
    public UUID groupTypeUuid;

    public GroupData(Group group) {
        this.uniqueId = group.uniqueId;
        this.groupName = group.groupName;
        this.members = new HashSet<>();
        members.addAll(group.members);
        this.groupTypeUuid = group.groupType;
    }
}
