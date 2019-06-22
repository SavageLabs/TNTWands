package me.driftay.tntwand.hooks.impl.factions;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.listeners.FactionsBlockListener;
import me.driftay.tntwand.hooks.impl.FactionHook;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import static me.driftay.tntwand.utils.Utils.color;
import static me.driftay.tntwand.utils.Utils.config;

public class FactionUUIDHook extends FactionHook {

    @Override
    public boolean canBuild(Block block, Player player) {
        if (!FactionsBlockListener.playerCanBuildDestroyBlock(player, block.getLocation(), "build", true)) {
            Faction otherFaction = Board.getInstance().getFactionAt(new FLocation(block.getLocation()));
            player.sendMessage(color(config.getString("Valid-Chunks.Deny-Message").replace("%faction%", otherFaction.getTag())));
            return false;
        }
        return true;
    }
}