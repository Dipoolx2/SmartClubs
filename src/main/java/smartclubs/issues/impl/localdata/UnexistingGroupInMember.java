package smartclubs.issues.impl.localdata;

import smartclubs.issues.Issue;
import smartclubs.issues.IssueCategory;

import java.util.UUID;

public class UnexistingGroupInMember extends Issue {
    public UUID memberUuid, groupTypeUuid, groupUuid;
    public UnexistingGroupInMember(UUID memberUuid, UUID groupTypeUuid, UUID groupUuid) {
        super("unexisting_group_in_member", "Unexisting/deleted group in member.", IssueCategory.LocalDataStorage);
        this.memberUuid = memberUuid;
        this.groupUuid = groupUuid;
        this.groupTypeUuid = groupTypeUuid;
    }

}
