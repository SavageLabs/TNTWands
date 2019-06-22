package me.driftay.tntwand.hooks;

public interface PluginHook<T> {

    T setup();

    String getName();


}
