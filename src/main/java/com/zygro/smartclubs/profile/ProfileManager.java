package com.zygro.smartclubs.profile;

import com.zygro.smartclubs.SmartClubs;
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
                initializeNewProfile(event.getPlayer());
                System.out.println("Adding player " + event.getPlayer().getName() + " to profiles.");
            }
        }, SmartClubs.INSTANCE);
    }

    public void initializeNewProfile(OfflinePlayer player) {
        this.profiles.add(new PlayerProfile(player));
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
