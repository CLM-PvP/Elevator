package org.sunlightdev;

/*

Lukas - 18:41
14.08.2023
https://github.com/NightDev701

© SunLightScorpion 2020 - 2023

*/

import org.bukkit.plugin.java.JavaPlugin;
import org.sunlightdev.listener.TeleportListener;

public class ElevatorPlugin extends JavaPlugin {

    private static ElevatorPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new TeleportListener(), this);
    }

    public static ElevatorPlugin getPlugin() {
        return plugin;
    }

}
