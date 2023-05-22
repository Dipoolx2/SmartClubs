package smartclubs.group.management;

import java.util.Objects;
import java.util.UUID;

public class GroupType {
    private final UUID uniqueId;
    protected String groupTypeName;

    public GroupType(String groupTypeName) {
        this.uniqueId = UUID.randomUUID();
        this.groupTypeName = groupTypeName;
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
        GroupType otherGroupType = (GroupType) obj;
        return Objects.equals(uniqueId, otherGroupType.uniqueId);
    }

}
