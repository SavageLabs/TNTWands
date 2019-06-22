package me.driftay.tntwand.hooks.impl;


import me.driftay.tntwand.SavageTnTWand;
import me.driftay.tntwand.hooks.PluginHook;
import me.driftay.tntwand.hooks.impl.factions.FactionUUIDHook;
import me.driftay.tntwand.utils.Logger;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;

public class FactionHook implements PluginHook<FactionHook> {

    @Override
    public FactionHook setup() {
        if (SavageTnTWand.instance.getServer().getPluginManager().getPlugin(getName()) == null) {
            Logger.print("Factions could not be found", Logger.PrefixType.WARNING);
            return null;
        }
        List<String> authors = SavageTnTWand.instance.getServer().getPluginManager().getPlugin(getName()).getDescription().getAuthors();
        if (!authors.contains("drtshock") || !authors.contains("Benzimmer")) {
            return null;
        } else {
            Logger.print("Server Factions type has been set to (FactionsUUID/SavageFactions/FactionsUltimate)", Logger.PrefixType.DEFAULT);
            return new FactionUUIDHook();
        }
    }

    public boolean canBuild(Block block, Player player) {
        throw new NotImplementedException("Factions does not exist!");
    }

    @Override
    public String getName() {
        return "Factions";
    }

}
