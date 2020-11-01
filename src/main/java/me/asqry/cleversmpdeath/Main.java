package me.asqry.cleversmpdeath;

import me.asqry.cleversmpdeath.commands.ToggleCoords;
import me.asqry.cleversmpdeath.listeners.PlayerDies;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin implements Listener {
    public static List<Player> truePlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        System.out.println("\n" +
                "   _____ _                       _   _____             _   _     \n" +
                "  / ____| |                     | | |  __ \\           | | | |    \n" +
                " | |    | | _____   _____ _ __  | | | |  | | ___  __ _| |_| |__  \n" +
                " | |    | |/ _ \\ \\ / / _ \\ '__| | | | |  | |/ _ \\/ _` | __| '_ \\ \n" +
                " | |____| |  __/\\ V /  __/ |    | | | |__| |  __/ (_| | |_| | | |\n" +
                "  \\_____|_|\\___| \\_/ \\___|_|    | | |_____/ \\___|\\__,_|\\__|_| |_|\n" +
                "                                | |                              \n" +
                "                                |_|                              \n");

        Bukkit.getPluginManager().registerEvents(new PlayerDies(), this);
        Bukkit.getPluginManager().registerEvents(new ToggleCoords(), this);
        this.getCommand("togglecoords").setExecutor(new ToggleCoords());
    }

    @Override
    public void onDisable() {
        System.out.println("Shutting Down...");
    }
}
