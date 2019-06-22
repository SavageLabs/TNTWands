package me.driftay.tntwand.events;

import com.massivecraft.factions.*;
import com.massivecraft.factions.listeners.FactionsBlockListener;
import me.driftay.tntwand.hooks.HookManager;
import me.driftay.tntwand.hooks.impl.WorldGuardHook;
import me.driftay.tntwand.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.driftay.tntwand.utils.Utils.color;
import static me.driftay.tntwand.utils.Utils.config;

public class InteractEvent implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;

        Action action = event.getAction();
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        ItemMeta im = itemInHand.getItemMeta();
        String displayname = color(Utils.config.getString("Item.Display-Name"));
        String successMessage = color(Utils.config.getString("SavageTnTWand.Success-Message"));
        FPlayer fplayer = FPlayers.getInstance().getByPlayer(event.getPlayer());
        Faction otherFaction = Board.getInstance().getFactionAt(new FLocation(block.getLocation()));

        Faction faction = fplayer.getFaction();
        if (itemInHand.getType().equals(Material.GOLD_HOE)) {
            if (im.getDisplayName().equalsIgnoreCase(displayname)) {
                if (player.getGameMode().equals(GameMode.CREATIVE)) {
                    event.setCancelled(true);
                    return;
                }
                if (action.equals(Action.RIGHT_CLICK_BLOCK)
                        && (block.getType().equals(Material.CHEST)
                        || block.getType().equals(Material.TRAPPED_CHEST))) {

                    Chest chest = (Chest) block.getState();

                    if (HookManager.getPluginMap().get("WorldGuard") != null) {
                        WorldGuardHook wgHook = ((WorldGuardHook) HookManager.getPluginMap().get("WorldGuard"));
                        if (!wgHook.canBuild(player, block)) {
                            event.getPlayer().sendMessage(color(config.getString("Valid-Chunks.Deny-Message").replace("%faction%", otherFaction.getTag())));
                            return;
                        }
                    }
                    if (!FactionsBlockListener.playerCanBuildDestroyBlock(player, block.getLocation(), "build", true)) {
                        event.setCancelled(true);
                        player.sendMessage(color(config.getString("Valid-Chunks.Deny-Message").replace("%faction%", otherFaction.getTag())));
                        return;
                    }
                    int tntcount = 0;
                    Inventory inventory = chest.getInventory();
                    for (int i = 0; i < inventory.getSize(); ++i) {
                        ItemStack tnt = inventory.getItem(i);
                        if (tnt != null && tnt.getType() == Material.TNT) {
                            tntcount += tnt.getAmount();
                            inventory.removeItem(tnt);
                        }
                    }
                    if (tntcount > 0) {
                        successMessage = successMessage.replace("%amount%", Integer.toString(tntcount));
                        faction.addTnt(tntcount);
                        player.sendMessage(successMessage);
                    }
                }
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Action action = e.getAction();
        Player player = e.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        ItemMeta im = itemInHand.getItemMeta();
        String displayname = color(Utils.config.getString("Item.Display-Name"));
        Block block = e.getClickedBlock();
        if (block == null) return;

        if (itemInHand.getType().equals(Material.GOLD_HOE)) {
            if (im.getDisplayName().equalsIgnoreCase(displayname)) {
                if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (block.getType() == Material.GRASS || block.getType() == Material.DIRT) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}

