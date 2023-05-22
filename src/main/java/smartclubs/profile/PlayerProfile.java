package smartclubs.profile;

import org.bukkit.OfflinePlayer;

import java.util.Objects;
import java.util.UUID;

public class PlayerProfile {
    public final UUID uniqueId;
    protected OfflinePlayer profileOwner;

    public PlayerProfile(OfflinePlayer offlinePlayer) {
        this.profileOwner = offlinePlayer;
        this.uniqueId = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId.hashCode());
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
        return Objects.equals(uniqueId, otherProfile.uniqueId);
    }
}
