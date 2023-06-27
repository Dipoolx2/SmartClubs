package smartclubs.issues.impl.localdata;

import smartclubs.SmartClubs;
import smartclubs.data.local.manager.ProfileDataManager;
import smartclubs.issues.Issue;
import smartclubs.issues.IssueCategory;

import java.util.UUID;

public class UnexistingGroupTypeInMember extends Issue {

    private final UUID memberUuid;
    private final UUID groupTypeUuid;

    public UnexistingGroupTypeInMember(UUID memberUuid, UUID groupTypeUuid) {
        super("unexisting_grouptype_in_member", "Unexisting/deleted group type in member.", IssueCategory.LocalDataStorage);
        this.memberUuid = memberUuid;
        this.groupTypeUuid = groupTypeUuid;

        this.issueFix = this::fix;
    }
    private boolean fix() {
        ProfileDataManager pdm = SmartClubs.INSTANCE.dataManager.getProfileDataManager();
        return pdm.deleteGroupsOfTypeFromProfile(memberUuid, groupTypeUuid);
    }
}
