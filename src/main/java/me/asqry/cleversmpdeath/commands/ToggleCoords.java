package me.asqry.cleversmpdeath.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static me.asqry.cleversmpdeath.Main.truePlayers;

public class ToggleCoords implements CommandExecutor, Listener {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You can't use this from console");

            return true;
        }

        Player p = (Player) sender;

        if(truePlayers.contains(p)){
            //if they're currently getting their coords
            truePlayers.remove(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully &c&ldisabled &acoordinate readout on death."));

        }else {
            //if they're not :P
            truePlayers.add(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully &lenabled &acoordinate readout on death."));

        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

            truePlayers.add(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully &lenabled &acoordinate readout on death."));
    }
}
