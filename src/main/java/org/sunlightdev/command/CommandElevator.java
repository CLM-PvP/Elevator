package org.sunlightdev.command;

/*

Lukas - 20:59
26.08.2023
https://github.com/NightDev701

© SunLightScorpion 2020 - 2023

*/

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sunlightdev.api.ElevatorAPI;

public class CommandElevator implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player p){
            double cost = ElevatorAPI.getCost();

            try {
                if(Economy.hasEnough(p.getName(), cost)){
                    Economy.subtract(p.getName(), cost);
                    p.getInventory().addItem(ElevatorAPI.getElevator());
                    p.sendMessage(ElevatorAPI.getPrefix()+"§aDu hast einen Aufzug erworben!");
                } else {
                    p.sendMessage(ElevatorAPI.getPrefix()+"§cDu hast nicht genug Geld!");
                }
            } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
                Bukkit.getOnlinePlayers().forEach(staff -> {
                    if(staff.hasPermission("elevator.warning")) {
                        staff.sendMessage("§4§l(!) Error: "+e.getMessage());
                    }
                });
            }

        }
        return false;
    }

}
