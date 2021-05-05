package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;

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

}
