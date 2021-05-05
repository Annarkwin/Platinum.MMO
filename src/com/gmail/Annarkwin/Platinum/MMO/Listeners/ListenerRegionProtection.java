package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Slime;
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
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.PortalCreateEvent.CreateReason;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.gmail.Annarkwin.Platinum.API.Events.PlayerBlockStepEvent;
import com.gmail.Annarkwin.Platinum.API.Events.PlayerRightClickBlockEvent;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class ListenerRegionProtection implements Listener
{

	private static final ArrayList<Material> prot_click_lockables = new ArrayList<Material>(Arrays.asList(
			Material.ACACIA_BUTTON, Material.BIRCH_BUTTON, Material.CRIMSON_BUTTON, Material.DARK_OAK_BUTTON,
			Material.JUNGLE_BUTTON, Material.OAK_BUTTON, Material.POLISHED_BLACKSTONE_BUTTON, Material.SPRUCE_BUTTON,
			Material.STONE_BUTTON, Material.WARPED_BUTTON,

			Material.LEVER,

			Material.ACACIA_DOOR, Material.ACACIA_TRAPDOOR, Material.BIRCH_DOOR, Material.BIRCH_TRAPDOOR,
			Material.CRIMSON_DOOR, Material.CRIMSON_TRAPDOOR, Material.DARK_OAK_DOOR, Material.DARK_OAK_TRAPDOOR,
			Material.IRON_DOOR, Material.IRON_TRAPDOOR, Material.JUNGLE_DOOR, Material.JUNGLE_TRAPDOOR,
			Material.OAK_DOOR, Material.OAK_TRAPDOOR, Material.SPRUCE_DOOR, Material.SPRUCE_TRAPDOOR,
			Material.WARPED_DOOR, Material.WARPED_TRAPDOOR,

			Material.ACACIA_FENCE_GATE, Material.BIRCH_FENCE_GATE, Material.CRIMSON_FENCE_GATE,
			Material.DARK_OAK_FENCE_GATE, Material.JUNGLE_FENCE_GATE, Material.OAK_FENCE_GATE,
			Material.SPRUCE_FENCE_GATE, Material.WARPED_FENCE_GATE));

	private static final ArrayList<Material> prot_step_lockables = new ArrayList<Material>(Arrays.asList(
			Material.ACACIA_PRESSURE_PLATE, Material.BIRCH_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE,
			Material.DARK_OAK_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.JUNGLE_PRESSURE_PLATE,
			Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.OAK_PRESSURE_PLATE,
			Material.POLISHED_BLACKSTONE_PRESSURE_PLATE, Material.SPRUCE_PRESSURE_PLATE, Material.STONE_PRESSURE_PLATE,
			Material.WARPED_PRESSURE_PLATE));

	// TODO Signs, Flower pot, and bed variants are not protected!
	private static final ArrayList<Material> prot_blocks = new ArrayList<Material>(
			Arrays.asList(Material.BEACON, Material.CAULDRON, Material.COMPARATOR, Material.REPEATER,
					Material.END_PORTAL_FRAME, Material.FLOWER_POT, Material.JUKEBOX, Material.RESPAWN_ANCHOR,
					Material.NOTE_BLOCK, Material.FLOWER_POT, Material.SWEET_BERRY_BUSH));

	private static final ArrayList<EntityType> prot_entities = new ArrayList<EntityType>(Arrays.asList(
			EntityType.ARMOR_STAND, EntityType.BEE, EntityType.CAT, EntityType.CHICKEN, EntityType.COW,
			EntityType.DONKEY, EntityType.ENDER_CRYSTAL, EntityType.FOX, EntityType.HORSE, EntityType.ITEM_FRAME,
			EntityType.LEASH_HITCH, EntityType.LLAMA, EntityType.MINECART, EntityType.MINECART_CHEST,
			EntityType.MINECART_COMMAND, EntityType.MINECART_FURNACE, EntityType.MINECART_HOPPER,
			EntityType.MINECART_MOB_SPAWNER, EntityType.MINECART_TNT, EntityType.MULE, EntityType.MUSHROOM_COW,
			EntityType.OCELOT, EntityType.PAINTING, EntityType.PANDA, EntityType.PARROT, EntityType.PIG,
			EntityType.POLAR_BEAR, EntityType.RABBIT, EntityType.SHEEP, EntityType.STRIDER, EntityType.TRADER_LLAMA,
			EntityType.TURTLE, EntityType.VILLAGER, EntityType.WOLF));

	private static final ArrayList<PotionType> safe_potions = new ArrayList<PotionType>(
			Arrays.asList(PotionType.FIRE_RESISTANCE, PotionType.INSTANT_HEAL, PotionType.JUMP, PotionType.LUCK,
					PotionType.NIGHT_VISION, PotionType.REGEN, PotionType.SLOW_FALLING, PotionType.SPEED,
					PotionType.STRENGTH, PotionType.WATER_BREATHING));

	private static final ArrayList<Material> prot_no_place = new ArrayList<Material>(
			Arrays.asList(Material.END_CRYSTAL, Material.MINECART, Material.CHEST_MINECART, Material.FURNACE_MINECART,
					Material.HOPPER_MINECART, Material.TNT_MINECART, Material.COMMAND_BLOCK_MINECART,
					Material.ACACIA_BOAT, Material.BIRCH_BOAT, Material.DARK_OAK_BOAT, Material.JUNGLE_BOAT,
					Material.OAK_BOAT, Material.SPRUCE_BOAT, Material.ARMOR_STAND));

	// TODO TEST ALL AND DEBUG
	// TODO Send messages

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
	public void placeHangingListener( HangingPlaceEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
				e.setCancelled(true);

	}

//	// ---------------------------------------
//	// A: Prevent place in disallowed zones
//	// ---------------------------------------
//	@EventHandler(ignoreCancelled = false)
//	public void placeEntityListener( EntityPlaceEvent e )
//	{
//
//		spawnReason.Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());
//
//		if (r != null)
//			if (!r.isAllowed(e.getPlayer()))
//				e.setCancelled(true);
//
//	}

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

		if (r != null)
			e.setCancelled(true);

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

		for (Block b : e.blockList())
		{

			Region blockr = MMO.region_manager.getRegion(b.getLocation());

			if (r != blockr)
			{

				e.setCancelled(true);
				return;

			}

		}

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
	// A:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void growBlockListener( BlockGrowEvent e )
	{

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
	// A: Prevent unallowed players from frostwalking in regions
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eFormBlockListener( EntityBlockFormEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
		{

			if (e.getEntity() instanceof Player && !r.isAllowed((Player) e.getEntity()))
				e.setCancelled(true);

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pInteractEntityListener( PlayerInteractEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getRightClicked().getLocation());

		if (r != null && !r.isAllowed(e.getPlayer()) && prot_entities.contains(e.getRightClicked().getType()))
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pArmorStandListener( PlayerArmorStandManipulateEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getRightClicked().getLocation());

		if (r != null && !r.isAllowed(e.getPlayer()) && prot_entities.contains(e.getRightClicked().getType()))
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pBucketFillListener( PlayerBucketFillEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
				e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pBucketEmptyListener( PlayerBucketEmptyEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
				e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pShearListener( PlayerShearEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null && !r.isAllowed(e.getPlayer()))
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pUnleashListener( PlayerUnleashEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null && !r.isAllowed(e.getPlayer()))
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pLeashListener( PlayerLeashEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null && !r.isAllowed(e.getPlayer()))
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void areaEffectCloudListener( AreaEffectCloudApplyEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDoorBreakListener( EntityBreakDoorEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent Flame & Fire Aspect combustion for protected
	// animals/villagers/players
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eCombustListener( EntityCombustByEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
		{

			if (prot_entities.contains(e.getEntity().getType()))
			{

				if (e.getCombuster() instanceof Player)
				{

					// Protect from unallowed players
					if (!r.isAllowed((Player) e.getCombuster()))
						e.setCancelled(true);

				}
				else if (e.getCombuster() instanceof Projectile)
				{

					// Protect from all projectiles - Prevents players from using mobs to kill your
					// entities
					if (((Projectile) e.getCombuster()).getShooter() instanceof Player
							&& !r.isAllowed((Player) ((Projectile) e.getCombuster()).getShooter()))
						e.setCancelled(true);

				}

			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDamageByBlockListener( EntityDamageByBlockEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDamageByEntityListener( EntityDamageByEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
		{

			if (prot_entities.contains(e.getEntity().getType()))
			{

				if (e.getDamager() instanceof Player)
				{

					// Protect from unallowed players
					if (!r.isAllowed((Player) e.getDamager()))
						e.setCancelled(true);

				}
				else if (e.getDamager() instanceof Projectile)
				{

					// Protect from all projectiles - Prevents players from using mobs to kill your
					// entities
					Projectile proj = (Projectile) e.getDamager();

					if (proj.getShooter() instanceof Player)
					{

						Player shooter = (Player) proj.getShooter();

						if (!r.isAllowed(shooter))
							e.setCancelled(true);

					}
					else
						e.setCancelled(true);

				}

			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void hangingBreakByEntityListener( HangingBreakByEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
		{

			if (prot_entities.contains(e.getEntity().getType()))
			{

				if (e.getRemover() instanceof Player)
				{

					// Protect from unallowed players
					if (!r.isAllowed((Player) e.getRemover()))
						e.setCancelled(true);

				}
				else if (e.getRemover() instanceof Projectile)
				{

					// Protect from all projectiles - Prevents players from using mobs to kill your
					// entities
					Projectile proj = (Projectile) e.getRemover();

					if (proj.getShooter() instanceof Player)
					{

						Player shooter = (Player) proj.getShooter();

						if (!r.isAllowed(shooter))
							e.setCancelled(true);

					}
					else
						e.setCancelled(true);

				}

			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDamageListener( EntityDamageEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eExplodeListener( EntityExplodeEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eInteractBlockListener( EntityInteractEvent e )
	{

//		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());
//
//		if (r != null)
//		{
//
//			if (e.getEntityType() == EntityType.PLAYER)
//			{
//
//				Player p = (Player) e.getEntity();
//
//				if (!r.isAllowed(p))
//				{
//
//					if (r.isLocked() && prot_lockables.contains(e.getBlock().getType()))
//						e.setCancelled(true);
//					else if (prot_blocks.contains(e.getBlock().getType()))
//						e.setCancelled(true);
//
//				}
//
//			}
//
//		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pInteractBlockListener( PlayerRightClickBlockEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
		{

			if (!r.isAllowed(e.getPlayer()))
			{

				if (r.isLocked() && prot_click_lockables.contains(e.getBlock().getType()))
					e.setCancelled(true);
				else if (prot_blocks.contains(e.getBlock().getType()))
					e.setCancelled(true);
				else if (prot_no_place.contains(e.getPlayer().getInventory().getItemInMainHand().getType()))
					e.setCancelled(true);
			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pInteractBlockListener( PlayerBlockStepEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
		{

			if (!r.isAllowed(e.getPlayer()))
			{

				if (r.isLocked() && prot_step_lockables.contains(e.getBlock().getType()))
					e.setCancelled(true);
				else if (prot_blocks.contains(e.getBlock().getType()))
					e.setCancelled(true);

			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void lingeringSplashListener( LingeringPotionSplashEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pigZapListener( PigZapEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void potionSplashListener( PotionSplashEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void projectileHitListener( ProjectileHitEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void vehicleEntityCollisionListener( VehicleEntityCollisionEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void vehicleCreateListener( VehicleCreateEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void vehicleDamageListener( VehicleDamageEvent e )
	{

		// TODO
	}

	// ---------------------------------------
	// TODO: test
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void portalCreateListener( PortalCreateEvent e )
	{

		if (e.getReason() == CreateReason.NETHER_PAIR)
		{

			for (BlockState b : e.getBlocks())
			{

				Region r = MMO.region_manager.getRegion(b.getLocation());

				if (r != null)
					e.setCancelled(true);

			}

		}

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

	@EventHandler(ignoreCancelled = false)
	public void regionEntitySpawn( EntitySpawnEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getLocation());

		if (r != null)
			if (!r.isMonsterSpawning() && (e.getEntity() instanceof Monster || e.getEntity() instanceof Hoglin
					|| e.getEntity() instanceof Ghast || e.getEntity() instanceof Phantom
					|| e.getEntity() instanceof Shulker || e.getEntity() instanceof Slime))
				e.setCancelled(true);
			else if (e.getEntity() instanceof Animals && !r.isAnimalSpawning())
				e.setCancelled(true);

	}

}
