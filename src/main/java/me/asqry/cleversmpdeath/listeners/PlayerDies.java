package me.asqry.cleversmpdeath.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static me.asqry.cleversmpdeath.Main.truePlayers;

public class PlayerDies implements Listener {

    public String sendDeathMessage(Player pl){
        String[] messages = {"&c{player} &7died...", "&9{player} &7lived a short life...", "&3{player} &7took social distancing a little too seriously", "&b{player} &7took a swift, quick slap from Karen's Ford Taurus", "&d{player} &7poofed"};

        int rand = (int) (Math.floor(Math.random() * messages.length));

        String parsedMsg = ChatColor.translateAlternateColorCodes('&', (messages[rand]).replace("{player}", pl.getDisplayName()));

        return parsedMsg;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if(e.getKeepInventory() == true) return;

        Player p = e.getEntity().getPlayer();
        List<ItemStack> items = e.getDrops();
        Location chestLoc = p.getLocation();

        if(truePlayers.contains(p)){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your death location (X,Y,Z): &a" + Math.ceil(chestLoc.getX()) + ", " + Math.ceil(chestLoc.getY()) + ", " + Math.ceil(chestLoc.getZ())));
        }

        if(items.size() != 0){
            chestLoc.getBlock().setType(Material.CHEST);
            Block chest = chestLoc.getBlock();

            if((chest.getType() != Material.CHEST)) chestLoc.getBlock().setType(Material.CHEST);

            Chest getChest = (Chest) chestLoc.getBlock().getState();
            Inventory chestInv = getChest.getBlockInventory();

            for(int i = 0; i < items.size(); i++){
                if(items.size() == 0) return;
                HashMap<Integer, ItemStack> didNotFit = chestInv.addItem(items.get(i));

                for(int it = 0; it < didNotFit.values().size(); it++){
                    ItemStack itemToDrop = didNotFit.get(0);
                    Location dropLoc = p.getLocation();
                    e.getEntity().getWorld().dropItem(dropLoc, itemToDrop);
                }
            }

            items.clear();
        }

        e.setDeathMessage(null);
        Bukkit.getServer().broadcastMessage(sendDeathMessage(p));
    }
}
