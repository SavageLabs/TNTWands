package me.driftay.tntwand.hooks;


import me.driftay.tntwand.SavageTnTWand;
import me.driftay.tntwand.utils.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HookManager {

    private static Map<String, PluginHook<?>> pluginMap = new HashMap<>();

    public HookManager(List<PluginHook<?>> list) {
        for (PluginHook<?> hook : list) {
            pluginMap.put(hook.getName(), (PluginHook<?>) hook.setup());
            if (SavageTnTWand.instance.getServer().getPluginManager().getPlugin(hook.getName()) != null) {
                Logger.print("successfully hooked " + hook.getName(), Logger.PrefixType.DEFAULT);
            } else {
                Logger.print("could not hook " + hook.getName(), Logger.PrefixType.WARNING);
            }
        }
    }

    public static Map<String, PluginHook<?>> getPluginMap() {
        return pluginMap;
    }

}

