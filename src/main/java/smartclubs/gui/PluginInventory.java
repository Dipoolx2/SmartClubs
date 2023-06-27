package smartclubs.gui;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import smartclubs.SmartClubs;
import smartclubs.profile.PlayerProfile;

public class PluginInventory implements Listener {

    private HashMap<Integer, PluginInventoryItem> inventoryItems;
    private Inventory inventory;
    private PlayerProfile user;

    private PluginInventory previous;
    private String title;
    private boolean closable;

    private int size;

    public PluginInventory(String title, PlayerProfile user, boolean closable, int size) {
        this.inventoryItems = new HashMap<Integer, PluginInventoryItem>();
        this.title = title;
        this.closable = closable;
        this.size = size;
        this.user = user;

        this.inventory = Bukkit.createInventory(new PluginInventoryHolder(inventory), this.size, this.title);

        registerEvents();

    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if(!event.getInventory().equals(inventory) || closable == true) return;
                Bukkit.getScheduler().runTask(SmartClubs.INSTANCE, () -> event.getPlayer().openInventory(inventory));
            }
        }, SmartClubs.INSTANCE);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onItemClick(InventoryClickEvent event) {
                if(!event.getInventory().equals(inventory)) return;
                if(!inventoryItems.containsKey(event.getSlot())) return;
                event.setCancelled(true);
                PluginInventoryItem clickedItem = inventoryItems.get(event.getSlot());
                clickedItem.getClickAction().execute(user);

            }
        }, SmartClubs.INSTANCE);
    }

    public void openInventory(PlayerProfile profile) {
        if(SmartClubs.INSTANCE.getServer().getOfflinePlayer(profile.profileOwnerUuid).isOnline()) {
            Player onlinePlayer = SmartClubs.INSTANCE.getServer().getPlayer(profile.profileOwnerUuid);
            assert onlinePlayer != null;
            onlinePlayer.closeInventory();
            onlinePlayer.openInventory(this.inventory);
        }
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setItem(int index, PluginInventoryItem item) {
        this.inventoryItems.put(index, item);
        this.inventory.setItem(index, item.getItemStack());
    }

    public PlayerProfile getUser() {
        return this.user;
    }

    public PluginInventory getPreviousInventory() {
        return this.previous;
    }

    public void setPreviousInventory(PluginInventory pluginInventory) {
        this.previous = pluginInventory;
    }
}
