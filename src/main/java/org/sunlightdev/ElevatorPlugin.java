package org.sunlightdev;

/*

Lukas - 18:41
14.08.2023
https://github.com/NightDev701

© SunLightScorpion 2020 - 2023

*/

import org.bukkit.plugin.java.JavaPlugin;

public class ElevatorPlugin extends JavaPlugin {

    private static ElevatorPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public static ElevatorPlugin getPlugin() {
        return plugin;
    }

}
