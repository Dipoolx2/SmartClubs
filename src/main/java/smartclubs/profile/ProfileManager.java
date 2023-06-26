package smartclubs.profile;

import smartclubs.SmartClubs;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class ProfileManager {
    private SmartClubs pl;
    private final HashMap<UUID, PlayerProfile> profiles;
    public ProfileManager(SmartClubs pl) {
        this.profiles = new HashMap<>();
        this.pl = pl;

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
                if (getPlayerProfileFromCache(event.getPlayer()) != null) return;
                if (!pl.SINGLE_SERVER_MODE) {
                    if (pl.dataManager.getPlayerProfile(event.getPlayer()) != null) {
                        registerProfileToCache(pl.dataManager.getPlayerProfile(event.getPlayer()));
                        return;
                    }
                }
                PlayerProfile newProfile = generateProfile(event.getPlayer());
                registerProfileToCache(newProfile);
                pl.dataManager.addProfileToData(newProfile);
            }
        }, pl);
    }

    public PlayerProfile generateProfile(OfflinePlayer player) {
        return new PlayerProfile(player.getUniqueId());
    }

    public void registerProfileToCache(PlayerProfile profile) {
        this.profiles.put(profile.profileOwnerUuid, profile);
    }

    public PlayerProfile getPlayerProfileFromCache(OfflinePlayer player) {
        return profiles.get(player.getUniqueId());
    }
}
