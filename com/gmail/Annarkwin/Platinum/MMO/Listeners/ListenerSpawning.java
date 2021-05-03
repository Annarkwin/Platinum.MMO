package com.gmail.Annarkwin.Platinum.MMO.Listeners;

//import java.util.ArrayList;

//import org.bukkit.entity.Entity;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.LivingEntity;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
//import org.bukkit.event.entity.EntitySpawnEvent;

public class ListenerSpawning implements Listener {

//	//Pillagers spawn way too frequently and don't despawn
//	@EventHandler (ignoreCancelled = true, priority = EventPriority.HIGH)
//	public void pillagerThrottle(EntitySpawnEvent e) {
//		if (e.getEntityType() == EntityType.PILLAGER) {
//			ArrayList<Entity> pillagers = new ArrayList<Entity>();
//			for (Entity ent : e.getEntity().getNearbyEntities(60, 40, 60)) {
//				if (ent.getType() == EntityType.PILLAGER) pillagers.add(ent);
//			}
//			if (pillagers.size() >= 5) e.setCancelled(true);
//			else {
//				((LivingEntity) e.getEntity()).setRemoveWhenFarAway(true);
//			}
//		}
//	}
//	
//	//Phantoms are stupid
//	@EventHandler (ignoreCancelled = true, priority = EventPriority.HIGH)
//	public void phantomThrottle(EntitySpawnEvent e) {
//		if (e.getEntityType() == EntityType.PHANTOM) {
//			e.setCancelled(true);
//		}
//	}
}
