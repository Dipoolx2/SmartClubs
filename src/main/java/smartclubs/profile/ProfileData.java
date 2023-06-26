package smartclubs.profile;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ProfileData {
    public final OfflinePlayer profileOwner;

    public ProfileData(PlayerProfile playerProfile) {
        this.profileOwner = playerProfile.profileOwner;
    }
}
