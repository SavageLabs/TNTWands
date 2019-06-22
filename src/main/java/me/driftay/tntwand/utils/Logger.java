package me.driftay.tntwand.utils;

import me.driftay.tntwand.SavageTnTWand;
import org.bukkit.ChatColor;

public class Logger {

    public static void print(String message, PrefixType type) {
        SavageTnTWand.instance.getServer().getConsoleSender().sendMessage(type.getPrefix() + message);
    }

    public enum PrefixType {

        WARNING(ChatColor.RED + "WARNING: "), NONE(""), DEFAULT(ChatColor.GOLD + "[TNTWAND] "), FAILED(ChatColor.RED + "FAILED: ");

        private String prefix;

        PrefixType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

    }

}
