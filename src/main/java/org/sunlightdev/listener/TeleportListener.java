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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.sunlightdev.ElevatorPlugin;
import org.sunlightdev.api.ElevatorAPI;
import org.sunlightdev.distance.Distance;

public class TeleportListener implements Listener {

    @EventHandler
    public void on(BlockPlaceEvent e){
        Player p = e.getPlayer();
        ItemStack hand = p.getInventory().getItemInMainHand();
        Block b = e.getBlock();

        if(hand.getType() == Material.SCULK_SENSOR){
            if(hand.getItemMeta() != null){
                if(ElevatorAPI.elevatorItem(hand.getItemMeta())){
                    ElevatorAPI.saveElevator(b.getLocation());
                }
            }
        }
    }

    @EventHandler
    public void on(BlockBreakEvent e){
        Block b = e.getBlock();

        if(ElevatorAPI.isElevator(b.getLocation())){
            ElevatorAPI.deleteElevator(b.getLocation());
        }
    }

    @EventHandler
    public void on(PlayerToggleSneakEvent e){
        Player p = e.getPlayer();

        for (int y = p.getLocation().getBlockY() - 1; y > 0; y--) {
            if (ElevatorAPI.isElevatorBlock(p, y)) {
                e.setCancelled(true);
                Location loc = new Location(p.getWorld(), p.getLocation().getX(), y + 0.6, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                if(ElevatorAPI.isElevator(loc)){
                    Bukkit.getScheduler().runTaskLater(ElevatorPlugin.getPlugin(), () -> p.teleport(loc), 3);
                    return;
                }
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
                if (ElevatorAPI.isElevatorBlock(p, y)) {
                    e.setCancelled(true);
                    Location loc = new Location(p.getWorld(), p.getLocation().getX(), y + 0.6, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                    if(ElevatorAPI.isElevator(loc)){
                        Bukkit.getScheduler().runTaskLater(ElevatorPlugin.getPlugin(), () -> p.teleport(loc), 3);
                        return;
                    }
                }
            }
        }
    }

}
