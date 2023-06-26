package smartclubs.profile;

import org.bukkit.OfflinePlayer;

import java.util.Objects;

public class PlayerProfile {
    protected OfflinePlayer profileOwner;

    public PlayerProfile(OfflinePlayer offlinePlayer) {
        this.profileOwner = offlinePlayer;
        System.out.println("Created profile for player " + offlinePlayer.getName());
        System.out.println("Created profile for player " + offlinePlayer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileOwner.getUniqueId().hashCode());
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
        return Objects.equals(profileOwner.getUniqueId(), otherProfile.profileOwner.getUniqueId());
    }
}
