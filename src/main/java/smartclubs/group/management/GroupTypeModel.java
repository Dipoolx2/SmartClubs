package smartclubs.group.management;

import java.util.UUID;

public class GroupTypeModel {
    public final UUID uniqueId;
    public String groupTypeName;

    public GroupTypeModel(GroupType groupType) {
        this.uniqueId = groupType.uniqueId;
        this.groupTypeName = groupType.groupTypeName;
    }
}
