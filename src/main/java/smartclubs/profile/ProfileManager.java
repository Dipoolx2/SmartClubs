package smartclubs.profile;

import smartclubs.SmartClubs;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;

public class ProfileManager {
    private SmartClubs pl;
    private final HashSet<PlayerProfile> profiles;
    public ProfileManager(SmartClubs pl) {
        this.profiles = new HashSet<>();
        this.pl = pl;

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
                if(getPlayerProfile(event.getPlayer()) != null) return;
                PlayerProfile newProfile = initializeNewProfile(event.getPlayer());
                SmartClubs.INSTANCE.localDataManager.profileData.writeProfile(newProfile);
            }
        }, SmartClubs.INSTANCE);
    }

    public PlayerProfile initializeNewProfile(OfflinePlayer player) {
        PlayerProfile newProfile = new PlayerProfile(player);
        this.profiles.add(newProfile);
        return newProfile;
    }

    public PlayerProfile getPlayerProfile(OfflinePlayer player) {
        for (PlayerProfile profile : profiles) {
            if (profile.profileOwner.getUniqueId().equals(player.getUniqueId())) {
                return profile;
            }
        }
        return null;
    }
}
