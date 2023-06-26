package smartclubs.group.management;

import java.util.UUID;

public class GroupTypeData {
    public final UUID uniqueId;
    public String groupTypeName;

    public GroupTypeData(GroupType groupType) {
        this.uniqueId = groupType.uniqueId;
        this.groupTypeName = groupType.groupTypeName;
    }
}
