package smartclubs.profile;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ProfileModel {
    public final UUID uniqueId;
    public final OfflinePlayer profileOwner;

    public ProfileModel(PlayerProfile playerProfile) {
        this.uniqueId = playerProfile.uniqueId;
        this.profileOwner = playerProfile.profileOwner;
    }
}
