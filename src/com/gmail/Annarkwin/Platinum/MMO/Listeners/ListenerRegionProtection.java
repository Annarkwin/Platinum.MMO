package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.world.PortalCreateEvent;

import com.gmail.Annarkwin.Platinum.API.Events.PlayerRightClickBlockEvent;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class ListenerRegionProtection implements Listener
{

	// TODO TEST ALL AND DEBUG

	// ---------------------------------------
	// A: Prevent break in disallowed zones
	// B: Allow quarry block breaking
	// ---------------------------------------
	// TODO Change to PlayerMineEvent
	@EventHandler(ignoreCancelled = false)
	public void breakBlockListener( BlockBreakEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
				e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent place in disallowed zones
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void placeBlockListener( BlockPlaceEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
				e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent place in disallowed zones
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void placeMultiBlockListener( BlockMultiPlaceEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
				e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent block burn
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void burnBlockListener( BlockBurnEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

	}

	// ---------------------------------------
	// A: Prevent firespread
	// B: Prevent flint & steel use in protected areas
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void igniteBlockListener( BlockIgniteEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null && e.getCause() == IgniteCause.FLINT_AND_STEEL)
			if (!r.isAllowed(e.getPlayer()))
				e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent block explosion
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void explodeBlockListener( BlockExplodeEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

	}

	// ---------------------------------------
	// A: Honestly no clue what this does
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void fromtoBlockListener( BlockFromToEvent e )
	{

		Region from = MMO.region_manager.getRegion(e.getBlock().getLocation());
		Region to = MMO.region_manager.getRegion(e.getToBlock().getLocation());

		if (from != to)
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent growth on server-pause
	// B: Prevent global growth if global-growth disabled
	// C: Prevent zone growth if zone-growth disabled
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void growBlockListener( BlockGrowEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

	}

	// ---------------------------------------
	// A: Prevent push into/out of zones
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pistonExtendBlockListener( BlockPistonExtendEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		for (Block b : e.getBlocks())
		{

			if (MMO.region_manager.getRegion(b.getLocation()) != r)
			{

				e.setCancelled(true);
				return;

			}

		}

	}

	// ---------------------------------------
	// A: Prevent pull into/out of zones
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pistonRetractBlockListener( BlockPistonRetractEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		for (Block b : e.getBlocks())
		{

			if (MMO.region_manager.getRegion(b.getLocation()) != r)
			{

				e.setCancelled(true);
				return;

			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void blockSpreadListener( BlockSpreadEvent e )
	{

		Region from = MMO.region_manager.getRegion(e.getSource().getLocation());
		Region to = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (from != to)
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eFormBlockListener( EntityBlockFormEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pInteractEntityListener( PlayerInteractEntityEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pArmorStandListener( PlayerArmorStandManipulateEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pBucketListener( PlayerBucketFillEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pBucketListener( PlayerBucketEmptyEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pShearListener( PlayerShearEntityEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pUnleashListener( PlayerUnleashEntityEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pLeashListener( PlayerLeashEntityEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void areaEffectCloudListener( AreaEffectCloudApplyEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDoorBreakListener( EntityBreakDoorEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

	}

	// ---------------------------------------
	// A: Prevent Flame & Fire Aspect combustion for protected
	// animals/villagers/players
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eCombustListener( EntityCombustByEntityEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDamageListener( EntityDamageByBlockEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDamageListener( EntityDamageByEntityEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDamageListener( EntityDamageEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eExplodeListener( EntityExplodeEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eInteractListener( EntityInteractEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void lingeringSplashListener( LingeringPotionSplashEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pigZapListener( PigZapEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void potionSplashListener( PotionSplashEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void projectileHitListener( ProjectileHitEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void vehicleEntityCollisionListener( VehicleEntityCollisionEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void vehicleCreateListener( VehicleCreateEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void vehicleDamageListener( VehicleDamageEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void portalCreateListener( PortalCreateEvent e )
	{

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pInteractListener( PlayerRightClickBlockEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
				if (e.getBlock().getState() instanceof Container)
					e.setCancelled(true);

	}

}
