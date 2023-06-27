package smartclubs.gui;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PluginInventoryItem {

    private Material displayMaterial;
    private String displayName;
    private List<String> lore;
    private InventoryClickAction clickAction;

    private boolean enchanted;

    public PluginInventoryItem(Material material, String displayName, List<String> lore, boolean isEnchanted, InventoryClickAction action) {
        this.displayMaterial = material;
        this.displayName = displayName;
        this.lore = lore;
        this.enchanted = isEnchanted;
        this.clickAction = action;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(this.displayMaterial);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(this.displayName);
        meta.setLore(this.lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        if(this.enchanted) meta.addEnchant(Enchantment.DURABILITY, 1, true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public InventoryClickAction getClickAction() {
        return this.clickAction;
    }

}
