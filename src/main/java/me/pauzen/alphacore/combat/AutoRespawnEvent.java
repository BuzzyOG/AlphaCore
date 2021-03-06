/*
 *  Created by Filip P. on 2/6/15 6:55 PM.
 */

package me.pauzen.alphacore.combat;

import me.pauzen.alphacore.events.CallablePlayerContainerEvent;
import me.pauzen.alphacore.players.CorePlayer;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class AutoRespawnEvent extends CallablePlayerContainerEvent {

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private EntityDamageEvent.DamageCause damageCause;

    public AutoRespawnEvent(CorePlayer corePlayer, EntityDamageEvent.DamageCause damageCause) {
        super(corePlayer);
        this.damageCause = damageCause;
    }

    public EntityDamageEvent.DamageCause getDamageCause() {
        return damageCause;
    }
}
