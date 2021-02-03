package com.molean.railteleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RailTeleportHandler implements Listener {
    public RailTeleportHandler() {
        Bukkit.getPluginManager().registerEvents(this, JavaPlugin.getPlugin(RailTeleportPlugin.class));
    }

    @EventHandler
    public void railTeleport(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("RailTeleport")) {
            //no permission
            return;
        }
        if (!event.isSneaking()) {
            //release shift
            return;
        }
        Block firstRail = player.getLocation().getBlock();
        if (!firstRail.getType().name().toLowerCase().contains("rail")) {
            //not rail
            return;
        }
        Direction direction = Direction.getPlayerFacedDirection(player);
        Block secondRail = RailUtils.getFollowingRail(direction, firstRail);
        if (secondRail == null) {
            //only one rail
            return;
        }
        Block lastRail = RailUtils.getLastConnectedRail(firstRail, secondRail);
        Location destination = player.getLocation();
        destination.setX(lastRail.getX() + destination.getX() - destination.getBlockX());
        destination.setY(lastRail.getY() + destination.getY() - destination.getBlockY());
        destination.setZ(lastRail.getZ() + destination.getZ() - destination.getBlockZ());

        Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(RailTeleportPlugin.class), () -> {
            RailTeleportEvent railTeleportEvent = new RailTeleportEvent(player, firstRail, lastRail, destination);
            Bukkit.getPluginManager().callEvent(railTeleportEvent);
            if (railTeleportEvent.isCancelled()) {
                //event canceled
                return;
            }
            Location finalDestination = railTeleportEvent.getDestination();
            player.teleport(finalDestination);
            Sound sound = railTeleportEvent.getSound();
            if (sound == null) {
                return;
            }
            player.getWorld().playSound(destination, sound, 1.0f, 1.0f);
        });
    }
}
