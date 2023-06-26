package smartclubs.profile;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ProfileData {
    public final UUID uniqueId;
    public final OfflinePlayer profileOwner;

    public ProfileData(PlayerProfile playerProfile) {
        this.uniqueId = playerProfile.uniqueId;
        this.profileOwner = playerProfile.profileOwner;
    }
}
