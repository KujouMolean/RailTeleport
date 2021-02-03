package com.molean.railteleport;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class RailTeleportEvent extends PlayerEvent implements Cancellable {
    private final HandlerList handlerList = new HandlerList();
    private boolean cancel = false;
    private final Block firstRail;
    private final Block lastRail;
    private Location destination;
    private Sound sound = Sound.ENTITY_IRON_GOLEM_ATTACK;

    public RailTeleportEvent(Player who, Block firstRail, Block lastRail, Location destination) {
        super(who);
        this.firstRail = firstRail;
        this.lastRail = lastRail;
        this.destination = destination;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public Block getFirstRail() {
        return firstRail;
    }

    public Block getLastRail() {
        return lastRail;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }
}
