package smartclubs.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class PluginInventoryHolder implements InventoryHolder {

    private Inventory inventory;

    public PluginInventoryHolder(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

}
