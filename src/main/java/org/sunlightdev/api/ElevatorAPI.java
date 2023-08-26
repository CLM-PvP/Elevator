package org.sunlightdev.api;

/*

Lukas - 20:39
26.08.2023
https://github.com/NightDev701

© SunLightScorpion 2020 - 2023

*/

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ElevatorAPI {

    public static void buildConfig(){
        File file = new File("plugins/Elevator/settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        if(!data.isSet("price")){
            data.set("prefix", "&8[&6Elevator&8] ");
            data.set("price", 250);
        }

        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPrefix(){
        File file = new File("plugins/Elevator/settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getString("prefix").replace("&", "§");
    }

    public static void saveElevator(Location location){

        String fileName = Objects.requireNonNull(location.getWorld()).getName()+"_"+location.getBlockX()+"_"+location.getBlockY()+"_"+location.getBlockZ()+".yml";
        File file = new File("plugins/Elevator/elevators/"+fileName);
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        data.set("elevator", true);

        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getOnlinePlayers().forEach(staff -> {
                if(staff.hasPermission("elevator.warning")) {
                    staff.sendMessage("§4§l(!) §c"+fileName+" can't saved! Message: "+e.getMessage());
                }
            });
        }
    }

    public static double getCost(){
        File file = new File("plugins/Elevator/settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getDouble("price");
    }

    public static void deleteElevator(Location location){
        String fileName = Objects.requireNonNull(location.getWorld()).getName()+"_"+location.getBlockX()+"_"+location.getBlockY()+"_"+location.getBlockZ()+".yml";
        File file = new File("plugins/Elevator/elevators/"+fileName);
        file.delete();
    }

    public static boolean isElevator(Location location){
        String fileName = Objects.requireNonNull(location.getWorld()).getName()+"_"+location.getBlockX()+"_"+location.getBlockY()+"_"+location.getBlockZ()+".yml";
        File file = new File("plugins/Elevator/elevators/"+fileName);
        return file.exists();
    }

    public static boolean elevatorItem(ItemMeta meta){
        return meta.getPersistentDataContainer().has(NamespacedKey.minecraft("elevator"), PersistentDataType.BYTE);
    }

    public static ItemStack getElevator(){
        ItemStack elevator = new ItemStack(Material.DAYLIGHT_DETECTOR);
        ItemMeta meta = elevator.getItemMeta();

        assert meta != null;

        meta.getPersistentDataContainer().set(NamespacedKey.minecraft("elevator"), PersistentDataType.BYTE, (byte) 1);

        elevator.setItemMeta(meta);
        return elevator;
    }

}
