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
                PlayerProfile newProfile = generateProfile(event.getPlayer());
                registerProfile(newProfile);
                SmartClubs.INSTANCE.localDataManager.profileData.writeProfile(newProfile);
            }
        }, SmartClubs.INSTANCE);
    }

    public PlayerProfile generateProfile(OfflinePlayer player) {
        return new PlayerProfile(player);
    }

    public void registerProfile(PlayerProfile profile) {
        this.profiles.add(profile);
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
