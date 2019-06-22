package me.driftay.tntwand.utils;

import me.driftay.tntwand.SavageTnTWand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static FileConfiguration config = SavageTnTWand.instance.getConfig();

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> string) {
        List<String> colored = new ArrayList<>();
        for (String line : string) {
            colored.add(color(line));
        }
        return colored;
    }

}
