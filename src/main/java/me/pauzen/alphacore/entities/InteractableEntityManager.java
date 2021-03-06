/*
 *  Created by Filip P. on 2/15/15 6:40 PM.
 */

package me.pauzen.alphacore.entities;

import me.pauzen.alphacore.listeners.ListenerImplementation;
import me.pauzen.alphacore.players.CorePlayer;
import me.pauzen.alphacore.utils.reflection.Nullify;
import me.pauzen.alphacore.utils.reflection.Registrable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;
import java.util.Map;

public class InteractableEntityManager extends ListenerImplementation implements Registrable {

    private Map<String, InteractableEntity> interactableEntities = new HashMap<>();
    
    public void registerInteractableEntity(InteractableEntity interactableEntity) {
        interactableEntities.put(interactableEntity.getEntity().getUniqueId().toString(), interactableEntity);
    }
    
    public InteractableEntity getInteractableEntity(String UUID) {
        return interactableEntities.get(UUID);
    }
    
    @Nullify
    private static InteractableEntityManager manager;
    
    public static void register() {
        manager = new InteractableEntityManager();
    }
    
    public static InteractableEntityManager getManager() {
        return manager;
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) e.getRightClicked();


            InteractableEntity interactableEntity = interactableEntities.get(livingEntity.getUniqueId().toString());

            if (interactableEntity != null) {
                interactableEntity.onClick(ClickType.RIGHT, CorePlayer.get(e.getPlayer()));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            
            if (e.getEntity() instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) e.getEntity();

                InteractableEntity interactableEntity = interactableEntities.get(livingEntity.getUniqueId().toString());

                if (interactableEntity != null) {
                    interactableEntity.onClick(ClickType.LEFT, CorePlayer.get(attacker));
                    
                    e.setCancelled(true);
                }
            }
        }
    }
}
