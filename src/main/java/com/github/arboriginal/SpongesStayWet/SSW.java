package com.github.arboriginal.SpongesStayWet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SSW extends JavaPlugin implements Listener {
    private final BlockData wetSponge = Material.WET_SPONGE.createBlockData();

    @Override
    public void onEnable() {
        super.onEnable();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private void onBlockPlace(BlockPlaceEvent e) {
        if (e.getItemInHand().getType() != Material.WET_SPONGE) return;
        Block b = e.getBlock();
        if (b.getWorld().getEnvironment() != Environment.NETHER) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                e.getBlock().setBlockData(wetSponge, false);
            }
        }.runTask(this);
    }
}
