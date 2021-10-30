package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class ListenerGameChanger implements Listener
{

	// Cancel magma damage if underwater
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void magmaDamage( EntityDamageEvent e )
	{

		if (e.getCause() == DamageCause.HOT_FLOOR && e.getEntityType() == EntityType.PLAYER)
		{

			if (e.getEntity().getLocation().getBlock().isLiquid())
				e.setCancelled(true);

		}

	}

	// Cancel fire burning blocks
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void blockBurn( BlockBurnEvent e )
	{

		if (e.getIgnitingBlock().getType() == Material.FIRE)
		{

			e.getIgnitingBlock().setType(Material.AIR);

		}

		e.setCancelled(true);

	}

	// Cancel fire spreading
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void fireSpread( BlockSpreadEvent e )
	{

		if (e.getSource().getType() == Material.FIRE)
		{

			e.setCancelled(true);

		}

	}

	// Cancel explosion damage to blocks
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void entityExplosion( EntityExplodeEvent e )
	{

		if (e.getLocation().getBlock().getLightFromSky() > 0)
			e.blockList().clear();

	}

	// Change spawned armorstands to have arms
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void armorStandAddArms( EntitySpawnEvent e )
	{

		if (e.getEntity() instanceof ArmorStand)
		{

			ArmorStand a = (ArmorStand) e.getEntity();
			a.setArms(true);
			a.setCanPickupItems(true);
			if (a.getLocation().getBlock().getType() == Material.REDSTONE_WIRE)
				a.setSmall(true);

		}

	}

	// Endermen no longer ruin terrain
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void endermanTouchStuff( EntityChangeBlockEvent e )
	{

		if (e.getEntity() instanceof Enderman)
		{

			e.setCancelled(true);

		}

	}

	// powered armorstands have a pose

//	// Change spawned armorstands to have arms
//	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
//	public void armorStandPowerPose( BlockRedstoneEvent e )
//	{
//
//		Collection<Entity> mobs = e.getBlock().getWorld().getNearbyEntities(e.getBlock().getBoundingBox());
//		
//		for (Entity target : mobs) {
//			if (target.getType() == EntityType.ARMOR_STAND)
//			{
//				
//			}
//		}
//
//	}

}
