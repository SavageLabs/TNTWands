package me.driftay.tntwand;


import me.driftay.tntwand.commands.CmdTntWand;
import me.driftay.tntwand.events.InteractEvent;
import me.driftay.tntwand.hooks.HookManager;
import me.driftay.tntwand.hooks.PluginHook;
import me.driftay.tntwand.hooks.impl.FactionHook;
import me.driftay.tntwand.hooks.impl.WorldGuardHook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static me.driftay.tntwand.utils.Utils.color;

public class SavageTnTWand extends JavaPlugin {

    public static SavageTnTWand instance;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        getCommand("tntwand").setExecutor(new CmdTntWand());
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        List<PluginHook<?>> hooks = new ArrayList<>();
        hooks.add(new FactionHook());
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
            hooks.add(new WorldGuardHook());
        }
        new HookManager(hooks);
    }

    public ItemStack createItem(Material material, int amount, short datavalue, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, datavalue);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color(name));
        meta.setLore(color(lore));
        item.setItemMeta(meta);
        return item;
    }
}
