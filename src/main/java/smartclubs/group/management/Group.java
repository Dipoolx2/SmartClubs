package smartclubs.group.management;

import smartclubs.profile.PlayerProfile;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Group {
    protected final UUID uniqueId;
    protected String groupName;
    protected HashSet<UUID> members;
    protected UUID groupType;

    public Group(String groupName, UUID groupType) {
        this.groupName = groupName;
        this.groupType = groupType;
        this.uniqueId = UUID.randomUUID();
        this.members = new HashSet<>();
    }

    public Group(UUID uuid, UUID groupType, String groupName, List<UUID> members) {
        this.uniqueId = uuid;
        this.groupType = groupType;
        this.groupName = groupName;
        this.members = new HashSet<>(members);
    }

    protected void addProfileToGroup(UUID profileUuid) {
        members.add(profileUuid);
    }

    protected boolean compareName(String supposedName) {
        return this.groupName.equalsIgnoreCase(supposedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Group otherGroup = (Group) obj;
        return Objects.equals(uniqueId, otherGroup.uniqueId);
    }
}
