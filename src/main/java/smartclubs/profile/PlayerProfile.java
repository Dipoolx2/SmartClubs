package smartclubs.profile;

import java.util.Objects;
import java.util.UUID;

public class PlayerProfile {
    protected UUID profileOwnerUuid;

    public PlayerProfile(UUID profileOwnerUuid) {
        this.profileOwnerUuid = profileOwnerUuid;
        System.out.println("Created profile for player " + profileOwnerUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileOwnerUuid.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlayerProfile otherProfile = (PlayerProfile) obj;
        return Objects.equals(profileOwnerUuid, otherProfile.profileOwnerUuid);
    }
}
