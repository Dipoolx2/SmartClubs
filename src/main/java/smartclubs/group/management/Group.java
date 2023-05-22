package smartclubs.group.management;

import smartclubs.profile.PlayerProfile;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class Group {
    protected final UUID uniqueId;
    protected String groupName;
    protected HashSet<PlayerProfile> members;
    protected GroupType groupType;

    public Group(String groupName, GroupType groupType) {
        this.groupName = groupName;
        this.groupType = groupType;
        this.uniqueId = UUID.randomUUID();
        this.members = new HashSet<>();
    }

    protected void addProfileToGroup(PlayerProfile profile) {
        members.add(profile);
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
