package org.sunlightdev.listener;

/*

Lukas - 18:29
22.08.2023
https://github.com/NightDev701

© SunLightScorpion 2020 - 2023

*/

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.sunlightdev.ElevatorPlugin;
import org.sunlightdev.distance.Distance;

public class TeleportListener implements Listener {

    @EventHandler
    public void on(PlayerToggleSneakEvent e){
        Player p = e.getPlayer();

        for (int y = p.getLocation().getBlockY() - 1; y > 0; y--) {
            if (p.getWorld().getBlockAt(p.getLocation().getBlockX(), y, p.getLocation().getBlockZ()).getType() == Material.DAYLIGHT_DETECTOR) {
                e.setCancelled(true);
                Location loc = new Location(p.getWorld(), p.getLocation().getX(), y + 0.6, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                Bukkit.getScheduler().runTaskLater(ElevatorPlugin.getPlugin(), () -> p.teleport(loc), 3);
                return;
            }
        }
    }

    @EventHandler
    public void on(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Distance distance = new Distance(e);
        Block b = p.getLocation().getBlock();

        if (distance.isGoingUp()) {
            for (int y = p.getLocation().getBlockY() + 1; y < 256; y++) {
                if (p.getWorld().getBlockAt(p.getLocation().getBlockX(), y, p.getLocation().getBlockZ()).getType() == Material.DAYLIGHT_DETECTOR) {
                    e.setCancelled(true);
                    Location loc = new Location(p.getWorld(), p.getLocation().getX(), y + 0.6, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                    Bukkit.getScheduler().runTaskLater(ElevatorPlugin.getPlugin(), () -> p.teleport(loc), 3);
                    return;
                }
            }
        }
    }

}
