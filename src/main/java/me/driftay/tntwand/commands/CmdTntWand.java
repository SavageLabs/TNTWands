package me.driftay.tntwand.commands;

import me.driftay.tntwand.SavageTnTWand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import static me.driftay.tntwand.utils.Utils.color;
import static me.driftay.tntwand.utils.Utils.config;

public class CmdTntWand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission(config.getString("SavageTnTWand.Reload-Permission"))) {
                SavageTnTWand.instance.reloadConfig();
                sender.sendMessage(color(config.getString("SavageTnTWand.Reloaded-Message")));
            } else {
                sender.sendMessage(color(config.getString("SavageTnTWand.No-Perms")));
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission(color(config.getString("SavageTnTWand.Give-Permission")))) {
                sender.sendMessage(color(config.getString("SavageTnTWand.No-Perms")));
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null || !Bukkit.getPlayer(args[1]).isOnline()) {
                sender.sendMessage(color(config.getString("SavageTnTWand.Not-Online")).replace("%player%", args[1]));
                return true;
            }
            if (Bukkit.getServer().getPlayer(args[1]).isOnline()) {
                ItemStack tntwand = SavageTnTWand.instance.createItem(Material.GOLD_HOE, 1, (short) 0, color(config.getString("Item.Display-Name")), color(config.getStringList("Item.lore")));
                Bukkit.getServer().getPlayer(args[1]).getInventory().addItem(tntwand);
                Bukkit.getServer().getPlayer(args[1]).sendMessage(color(config.getString("SavageTnTWand.Received-Messaged")));
                return true;
            }
        }
        if (args.length == 0 && sender.hasPermission(config.getString("SavageTnTWand.Give-Permission"))) {
            sender.sendMessage(color(""));
            sender.sendMessage(color("&b&m&l----&r&8&m&l[--- &4TNT &fWands &r&8&m&l---]&b&m&l---"));
            sender.sendMessage(color("&a» /&7tntwand give &a<&7&oplayer&a> - &7Gives a &aTnT Wand &7to given &aplayer"));
            sender.sendMessage(color(""));
            sender.sendMessage(color("&a» /&7tntwand reload &a- &7Reloads &aall &7files. &a(&7&oSome options require a server reload&a/&7&orestart&a)"));
        }
        return false;
    }
}
