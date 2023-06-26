package smartclubs.profile;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ProfileData {
    public final UUID profileOwnerUuid;

    public ProfileData(PlayerProfile playerProfile) {
        this.profileOwnerUuid = playerProfile.profileOwnerUuid;
    }
}
