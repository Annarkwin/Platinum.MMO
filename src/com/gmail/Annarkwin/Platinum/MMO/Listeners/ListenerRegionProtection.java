package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.LivingEntity;
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
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityUnleashEvent.UnleashReason;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingBreakEvent.RemoveCause;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.PortalCreateEvent.CreateReason;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

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
			Arrays.asList(Material.BEACON, Material.COMPARATOR, Material.REPEATER, Material.END_PORTAL_FRAME,
					Material.FLOWER_POT, Material.JUKEBOX, Material.RESPAWN_ANCHOR, Material.NOTE_BLOCK, Material.SWEET_BERRY_BUSH, Material.TNT));

	private static final ArrayList<PotionEffectType> safe_potions = new ArrayList<PotionEffectType>(Arrays.asList(
			PotionEffectType.FIRE_RESISTANCE, PotionEffectType.HEAL, PotionEffectType.JUMP, PotionEffectType.LUCK,
			PotionEffectType.NIGHT_VISION, PotionEffectType.REGENERATION, PotionEffectType.SLOW_FALLING,
			PotionEffectType.SPEED, PotionEffectType.INCREASE_DAMAGE, PotionEffectType.WATER_BREATHING));

	private static final ArrayList<Material> prot_no_place = new ArrayList<Material>(
			Arrays.asList(Material.END_CRYSTAL, Material.MINECART, Material.CHEST_MINECART, Material.FURNACE_MINECART,
					Material.HOPPER_MINECART, Material.TNT_MINECART, Material.COMMAND_BLOCK_MINECART,
					Material.ACACIA_BOAT, Material.BIRCH_BOAT, Material.DARK_OAK_BOAT, Material.JUNGLE_BOAT,
					Material.OAK_BOAT, Material.SPRUCE_BOAT, Material.ARMOR_STAND));

	private static final ArrayList<EntityType> prot_entities_always = new ArrayList<EntityType>(
			Arrays.asList(EntityType.ARMOR_STAND, EntityType.ITEM_FRAME, EntityType.MINECART, EntityType.MINECART_CHEST,
					EntityType.MINECART_COMMAND, EntityType.MINECART_FURNACE, EntityType.MINECART_HOPPER,
					EntityType.MINECART_MOB_SPAWNER, EntityType.MINECART_TNT, EntityType.PAINTING));

	private static final ArrayList<EntityType> prot_entities_pvpoff = new ArrayList<EntityType>(
			Arrays.asList(EntityType.BEE, EntityType.CAT, EntityType.CHICKEN, EntityType.COW, EntityType.DONKEY,
					EntityType.ENDER_CRYSTAL, EntityType.FOX, EntityType.HORSE, EntityType.LEASH_HITCH,
					EntityType.LLAMA, EntityType.MULE, EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.PANDA,
					EntityType.PARROT, EntityType.PIG, EntityType.PLAYER, EntityType.POLAR_BEAR, EntityType.RABBIT,
					EntityType.SHEEP, EntityType.STRIDER, EntityType.TRADER_LLAMA, EntityType.TURTLE,
					EntityType.VILLAGER, EntityType.WANDERING_TRADER, EntityType.WOLF));

	// TODO TEST ALL AND DEBUG
	// TODO Send messages

	// ---------------------------------------
	// A: Prevent break in disallowed regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pBreakBlockInRegion( BlockBreakEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
			{

				// FIXME Players get a message when mining in quarries - All messages should be sent through monitor
				// events
				// e.getPlayer().sendMessage("§2[Info]:§f You can't break that");
				e.setCancelled(true);

			}

	}

	// ---------------------------------------
	// A: Prevent placement of blocks in disallowed regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pPlaceBlockInRegion( BlockPlaceEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
			{

				e.getPlayer().sendMessage("§2[Info]:§f You can't place that here");
				e.setCancelled(true);

			}

	}

	// ---------------------------------------
	// A: Prevent placement of paintings and item frames in disallowed regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pPlaceHangingInRegion( HangingPlaceEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
			{

				e.getPlayer().sendMessage("§2[Info]:§f You can't place that here");
				e.setCancelled(true);

			}

	}

	// ---------------------------------------
	// A: Prevent placement of multi-blocks in disallowed regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pPlaceMultiInRegion( BlockMultiPlaceEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
			{

				e.getPlayer().sendMessage("§2[Info]:§f You can't place that here");
				e.setCancelled(true);

			}

	}

	// ---------------------------------------
	// A: Prevent block burn within regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bBurnInRegion( BlockBurnEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
		{

			e.setCancelled(true);

		}

	}

	// ---------------------------------------
	// A: Prevent firespread
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bIgniteInRegion( BlockIgniteEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			switch (e.getCause())
			{

			case ENDER_CRYSTAL:
				e.setCancelled(true);
				break;

			case EXPLOSION:
				e.setCancelled(true);
				break;

			case FIREBALL:
				e.setCancelled(true);
				break;

			case FLINT_AND_STEEL:
				Entity igniter = e.getIgnitingEntity();

				if (igniter != null && !r.isAllowed(igniter.getUniqueId()))
				{

					igniter.sendMessage("§2[Info]:§f You can't light that");
					e.setCancelled(true);

				}

				break;

			case LAVA:
				e.setCancelled(true);
				break;

			case LIGHTNING:
				e.setCancelled(true);
				break;

			case SPREAD:
				e.setCancelled(true);
				break;

			default:
				break;

			}

	}

	// ---------------------------------------
	// A: When a block explodes, blocks that aren't in the same region are protected
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bExplodeInRegion( BlockExplodeEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());
		ArrayList<Block> safeblocks = new ArrayList<Block>();

		for (Block b : e.blockList())
		{

			Region blockr = MMO.region_manager.getRegion(b.getLocation());

			if (r != blockr)
				safeblocks.add(b);

		}

		e.blockList().removeAll(safeblocks);

	}

	// ---------------------------------------
	// A: When an entity explodes, blocks that aren't in the same region are protected
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eExplodeInRegion( EntityExplodeEvent e )
	{

		ArrayList<Block> safeblocks = new ArrayList<Block>();

		for (Block b : e.blockList())
		{

			Region r = MMO.region_manager.getRegion(b.getLocation());

			if (r != null)
				safeblocks.add(b);

		}

		e.blockList().removeAll(safeblocks);

	}

	// ---------------------------------------
	// A: Prevent liquids from flowing into/out of/between regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bFlowInRegion( BlockFromToEvent e )
	{

		Region from = MMO.region_manager.getRegion(e.getBlock().getLocation());
		Region to = MMO.region_manager.getRegion(e.getToBlock().getLocation());

		if (from != to)
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent growth of plants in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bGrowInRegion( BlockGrowEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null && !r.isGrowing())
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent push into/out of regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bPistonPushInRegion( BlockPistonExtendEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		for (Block b : e.getBlocks())
		{

			if (MMO.region_manager.getRegion(b.getRelative(e.getDirection()).getLocation()) != r)
			{

				e.setCancelled(true);
				return;

			}

		}

	}

	// ---------------------------------------
	// A: Prevent pull into/out of regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bPistonRetractInRegion( BlockPistonRetractEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		for (Block b : e.getBlocks())
		{

			if (MMO.region_manager.getRegion(b.getRelative(e.getDirection().getOppositeFace()).getLocation()) != r)
			{

				e.setCancelled(true);
				return;

			}

		}

	}

	// ---------------------------------------
	// A: Prevent Mushrooms and Fire from spreading into/out of/between regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void bSpreadInRegion( BlockSpreadEvent e )
	{

		Region from = MMO.region_manager.getRegion(e.getSource().getLocation());
		Region to = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (from != to)
			e.setCancelled(true);

		if (from != null && !from.isGrowing())
			if (e.getSource().getType() == Material.BAMBOO || e.getSource().getType() == Material.BAMBOO_SAPLING || e.getSource().getType() == Material.CHORUS_FLOWER || e.getSource().getType() == Material.KELP)
				e.setCancelled(true);
		
	}

	// ---------------------------------------
	// A: Prevent disallowed players from frostwalking in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eFormBlockInRegion( EntityBlockFormEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
		{

			if (e.getEntity() instanceof Player)
				if (!r.isAllowed((Player) e.getEntity()))
					e.setCancelled(true);

		}

	}

	// ---------------------------------------
	// A: Prevent disallowed entity interaction in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = true)
	public void pInteractEntityInRegion( PlayerInteractEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getRightClicked().getLocation());

		if (r != null)
		{

			if (!r.isAllowed(e.getPlayer()))
			{

				// Always prevent interaction with these entities from disallowed players
				if (prot_entities_always.contains(e.getRightClicked().getType()))
				{

					e.getPlayer().sendMessage("§2[Info]:§f You can't interact with that here");
					e.setCancelled(true);

				}

				// If PVP is disabled, prevent interaction with an additional list of entities
				else if (!r.isPVP() && prot_entities_pvpoff.contains(e.getRightClicked().getType()))
				{

					e.getPlayer().sendMessage("§2[Info]:§f You can't interact with that here");
					e.setCancelled(true);

				}

			}

		}

	}

	// ---------------------------------------
	// A: Prevent disallowed player interaction with Armor Stands in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pUseArmorStandInRegion( PlayerArmorStandManipulateEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getRightClicked().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
			{

				e.getPlayer().sendMessage("§2[Info]:§f You can't interact with that here");
				e.setCancelled(true);

			}

	}

	// ---------------------------------------
	// A: Prevent disallowed players from filling buckets in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pBucketFillInRegion( PlayerBucketFillEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
			{

				e.getPlayer().sendMessage("§2[Info]:§f You can't fill that here");
				e.setCancelled(true);

			}

	}

	// ---------------------------------------
	// A: Prevent disallowed players from emptying buckets in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pBucketEmptyInRegion( PlayerBucketEmptyEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getPlayer()))
			{

				e.getPlayer().sendMessage("§2[Info]:§f You can't empty that here");
				e.setCancelled(true);

			}

	}

	// ---------------------------------------
	// A: Prevent disallowed players from shearing entities in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pShearEntityInRegion( PlayerShearEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null && !r.isAllowed(e.getPlayer()))
		{

			e.getPlayer().sendMessage("§2[Info]:§f You can't interact with that here");
			e.setCancelled(true);

		}

	}

	// ---------------------------------------
	// A: Prevent disallowed players from unleashing entities in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pUnleashEntityInRegion( PlayerUnleashEntityEvent e )
	{

		if (e.getReason() == UnleashReason.PLAYER_UNLEASH)
		{

			Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

			if (r != null)
				if (!r.isAllowed(e.getPlayer()))
				{

					e.getPlayer().sendMessage("§2[Info]:§f You can't interact with that here");
					e.setCancelled(true);

				}

		}

	}

	// ---------------------------------------
	// A: Prevent disallowed players from leashing entities in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pLeashEntityInRegion( PlayerLeashEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null && !r.isAllowed(e.getPlayer()))
		{

			e.getPlayer().sendMessage("§2[Info]:§f You can't interact with that here");
			e.setCancelled(true);

		}

	}

	// ---------------------------------------
	// A: Prevent Entities from breaking protected doors
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eDoorBreakInRegion( EntityBreakDoorEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent Flame & Fire Aspect combustion for protected animals/villagers/players
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eCombustByEntityInRegion( EntityCombustByEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
		{

			if (prot_entities_always.contains(e.getEntityType()))
				e.setCancelled(true);

			else if (!r.isPVP() && prot_entities_pvpoff.contains(e.getEntityType()))
			{

				if (e.getCombuster() instanceof Player)
				{

					// Protect from unallowed players
					if (!r.isAllowed((Player) e.getCombuster()))
						e.setCancelled(true);

				}
				else if (e.getCombuster() instanceof Projectile)
				{

					// Protect from all projectiles - Prevents players from using mobs to kill your entities
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
	public void eDamageByEntityInRegion( EntityDamageByEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
		{

			switch (e.getCause())
			{

			case BLOCK_EXPLOSION:
				if (prot_entities_always.contains(e.getEntityType()))
					e.setCancelled(true);
				break;

			case CONTACT:
				if (prot_entities_always.contains(e.getEntityType()))
					e.setCancelled(true);
				break;

			case CUSTOM:
				if (e.getDamager() instanceof Projectile)
				{

					ProjectileSource source = ((Projectile) e.getDamager()).getShooter();

					if (source instanceof Entity)
					{

						Entity shooter = (Entity) source;

						// Protect these entities always unless the shooter is an allowed player
						if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(shooter.getUniqueId()))
							e.setCancelled(true);
						// Protect these entities if pvp is off unless the shooter is an allowed player
						else if (!r.isPVP())
							if (e.getEntity() instanceof Player && !(shooter instanceof Player))
								return;
							else if (e.getEntity() instanceof Player && shooter instanceof Player)
								e.setCancelled(true);
							else if (prot_entities_pvpoff.contains(e.getEntityType())
									&& !r.isAllowed(shooter.getUniqueId()))
								e.setCancelled(true);

					}

				}
				// Protect these entities always unless the damager is an allowed player
				else if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(e.getDamager().getUniqueId()))
					e.setCancelled(true);
				// Protect these entities if pvp is off unless the damager is an allowed player
				else if (!r.isPVP())
					if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player))
						return;
					else if (e.getEntity() instanceof Player && e.getDamager() instanceof Player)
						e.setCancelled(true);
					else if (prot_entities_pvpoff.contains(e.getEntityType())
							&& !r.isAllowed(e.getDamager().getUniqueId()))
						e.setCancelled(true);
				break;

			case ENTITY_ATTACK:
				if (e.getDamager() instanceof Projectile)
				{

					ProjectileSource source = ((Projectile) e.getDamager()).getShooter();

					if (source instanceof Entity)
					{

						Entity shooter = (Entity) source;

						// Protect these entities always unless the shooter is an allowed player
						if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(shooter.getUniqueId()))
						{

							shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
							e.setCancelled(true);

						}
						// Protect these entities if pvp is off unless the shooter is an allowed player
						else if (!r.isPVP())
							if (e.getEntity() instanceof Player && !(shooter instanceof Player))
								return;
							else if (e.getEntity() instanceof Player && shooter instanceof Player)
							{

								shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
								e.setCancelled(true);

							}
							else if (prot_entities_pvpoff.contains(e.getEntityType())
									&& !r.isAllowed(shooter.getUniqueId()))
							{

								shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
								e.setCancelled(true);

							}

					}

				}
				// Protect these entities always unless the damager is an allowed player
				else if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(e.getDamager().getUniqueId()))
				{

					e.getDamager().sendMessage("§2[Info]:§f You can't hit that");
					e.setCancelled(true);

				}
				// Protect these entities if pvp is off unless the damager is an allowed player
				else if (!r.isPVP())
					if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player))
						return;
					else if (e.getEntity() instanceof Player && e.getDamager() instanceof Player)
					{
						e.getDamager().sendMessage("§2[Info]:§f You can't hit that");
						e.setCancelled(true);

					}
					else if (prot_entities_pvpoff.contains(e.getEntityType())
							&& !r.isAllowed(e.getDamager().getUniqueId()))
					{

						e.getDamager().sendMessage("§2[Info]:§f You can't hit that");
						e.setCancelled(true);

					}
				break;

			case ENTITY_EXPLOSION:
				if (e.getDamager() instanceof Projectile)
				{

					ProjectileSource source = ((Projectile) e.getDamager()).getShooter();

					if (source instanceof Entity)
					{

						Entity shooter = (Entity) source;

						// Protect these entities always unless the shooter is an allowed player
						if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(shooter.getUniqueId()))
							e.setCancelled(true);
						// Protect these entities if pvp is off unless the shooter is an allowed player
						else if (!r.isPVP())
							if (e.getEntity() instanceof Player && !(shooter instanceof Player))
								return;
							else if (e.getEntity() instanceof Player && shooter instanceof Player)
							{

								shooter.sendMessage("§2[Info]:§f Your explosives won't work on that");
								e.setCancelled(true);

							}
							else if (prot_entities_pvpoff.contains(e.getEntityType())
									&& !r.isAllowed(shooter.getUniqueId()))
							{

								shooter.sendMessage("§2[Info]:§f Your explosives won't work on that");
								e.setCancelled(true);

							}

					}

				}
				// Protect these entities always unless the damager is an allowed player
				else if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(e.getDamager().getUniqueId()))
					e.setCancelled(true);
				// Protect these entities if pvp is off unless the damager is an allowed player
				else if (!r.isPVP())
					// Players still take damage from non-players
					if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player))
						return;
					else if (e.getEntity() instanceof Player && e.getDamager() instanceof Player)
						e.setCancelled(true);
					else if (prot_entities_pvpoff.contains(e.getEntityType())
							&& !r.isAllowed(e.getDamager().getUniqueId()))
						e.setCancelled(true);
				break;

			case ENTITY_SWEEP_ATTACK:
				// Protect these entities always unless the damager is an allowed player
				if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(e.getDamager().getUniqueId()))
					e.setCancelled(true);
				// Protect these entities if pvp is off unless the damager is an allowed player
				else if (!r.isPVP())
					// Players still take damage from non-players
					if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player))
						return;
					else if (e.getEntity() instanceof Player && e.getDamager() instanceof Player)
						e.setCancelled(true);
					else if (prot_entities_pvpoff.contains(e.getEntityType())
							&& !r.isAllowed(e.getDamager().getUniqueId()))
						e.setCancelled(true);
				break;

			case FIRE:
				if (prot_entities_always.contains(e.getEntityType()))
					e.setCancelled(true);
				break;

			case FIRE_TICK:
				if (prot_entities_always.contains(e.getEntityType()))
					e.setCancelled(true);
				break;

			case LIGHTNING:
				if (prot_entities_always.contains(e.getEntityType()))
					e.setCancelled(true);
				break;

			case MAGIC:
				if (e.getDamager() instanceof Projectile)
				{

					ProjectileSource source = ((Projectile) e.getDamager()).getShooter();

					if (source instanceof Entity)
					{

						Entity shooter = (Entity) source;

						// Protect these entities always unless the shooter is an allowed player
						if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(shooter.getUniqueId()))
							e.setCancelled(true);
						// Protect these entities if pvp is off unless the shooter is an allowed player
						else if (!r.isPVP())
							// Players still take damage from non-players
							if (e.getEntity() instanceof Player && !(shooter instanceof Player))
								return;
							else if (e.getEntity() instanceof Player && shooter instanceof Player)
							{

								shooter.sendMessage("§2[Info]:§f Your magic won't work on that");
								e.setCancelled(true);

							}
							else if (prot_entities_pvpoff.contains(e.getEntityType())
									&& !r.isAllowed(shooter.getUniqueId()))
							{

								shooter.sendMessage("§2[Info]:§f Your magic won't work on that");
								e.setCancelled(true);

							}

					}

				}
				// Protect these entities always unless the damager is an allowed player
				else if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(e.getDamager().getUniqueId()))
				{

					e.getDamager().sendMessage("§2[Info]:§f Your magic won't work on that");
					e.setCancelled(true);

				}
				// Protect these entities if pvp is off unless the damager is an allowed player
				else if (!r.isPVP())
					// Players still take damage from non-players
					if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player))
						return;
					else if (e.getEntity() instanceof Player && e.getDamager() instanceof Player)
					{

						e.getDamager().sendMessage("§2[Info]:§f Your magic won't work on that");
						e.setCancelled(true);

					}
					else if (prot_entities_pvpoff.contains(e.getEntityType())
							&& r.isAllowed(e.getDamager().getUniqueId()))
					{

						e.getDamager().sendMessage("§2[Info]:§f Your magic won't work on that");
						e.setCancelled(true);

					}
				break;

			case PROJECTILE:
				ProjectileSource source = ((Projectile) e.getDamager()).getShooter();

				if (source instanceof Entity)
				{

					Entity shooter = (Entity) source;

					// Protect these entities always unless the shooter is an allowed player
					if (prot_entities_always.contains(e.getEntityType()) && !r.isAllowed(shooter.getUniqueId()))
					{

						shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
						e.setCancelled(true);

					}
					// Protect these entities if pvp is off unless the shooter is an allowed player
					else if (!r.isPVP())
						// Players still take damage from non-players
						if (e.getEntity() instanceof Player && !(shooter instanceof Player))
							return;
						else if (e.getEntity() instanceof Player && shooter instanceof Player)
						{

							shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
							e.setCancelled(true);

						}
						else if (prot_entities_pvpoff.contains(e.getEntityType())
								&& !r.isAllowed(shooter.getUniqueId()))
						{

							shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
							e.setCancelled(true);

						}

				}
				break;

			default:
				break;

			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void hangingBreakInRegion( HangingBreakEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null && e.getCause() != RemoveCause.ENTITY)
		{

			e.setCancelled(true);

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void hangingBreakByEntityInRegion( HangingBreakByEntityEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getEntity().getLocation());

		if (r != null)
		{

			switch (e.getCause())
			{

			case ENTITY:
				Entity breaker = e.getRemover();

				if (breaker instanceof Projectile)
				{

					ProjectileSource source = ((Projectile) breaker).getShooter();

					if (source instanceof Entity)
					{

						Entity shooter = (Entity) source;

						// Allow damage to these entities if the shooter is allowed
						if (prot_entities_always.contains(e.getEntity().getType())
								&& r.isAllowed(shooter.getUniqueId()))
							return;
						// Allow damage to these entities if the region is pvp enabled
						else if (prot_entities_pvpoff.contains(e.getEntity().getType()))
							if (r.isPVP())
								return;
							else
							{

								shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
								e.setCancelled(true);

							}
						else
						{

							shooter.sendMessage("§2[Info]:§f Your projectiles won't work on that");
							e.setCancelled(true);

						}

					}

				}
				else if (!r.isAllowed(breaker.getUniqueId()))
				{

					if (prot_entities_always.contains(e.getEntity().getType()))
					{

						breaker.sendMessage("§2[Info]:§f You can't break that");
						e.setCancelled(true);

					}
					else if (prot_entities_pvpoff.contains(e.getEntity().getType()) && !r.isPVP())
					{

						breaker.sendMessage("§2[Info]:§f You can't break that");
						e.setCancelled(true);

					}

				}
				break;

			default:
				break;

			}

		}

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
	// A: Prevent disallowed players from opening containers in regions
	// B: Prevent disallowed players from using certain blocks when locked in regions
	// C: Prevent disallowed players from using certain blocks ever in regions
	// D: Prevent disallowed players from trying to use certain items in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pRightClickBlockInRegion( PlayerRightClickBlockEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
		{

			if (!r.isAllowed(e.getPlayer()))
			{

				if (e.getBlock().getState() instanceof Container)
				{

					e.getPlayer().sendMessage("§2[Info]:§f You can't open that");
					e.setCancelled(true);

				}
				else if (r.isLocked() && prot_click_lockables.contains(e.getBlock().getType()))
				{

					e.getPlayer().sendMessage("§2[Info]:§f This region is locked");
					e.setCancelled(true);

				}
				else if (prot_blocks.contains(e.getBlock().getType()))
				{

					e.getPlayer().sendMessage("§2[Info]:§f You can't use that");
					e.setCancelled(true);

				}
				else if (prot_no_place.contains(e.getPlayer().getInventory().getItemInMainHand().getType()))
				{

					e.getPlayer().sendMessage("§2[Info]:§f You can't place that here");
					e.setCancelled(true);

				}
				else if (prot_no_place.contains(e.getPlayer().getInventory().getItemInOffHand().getType()))
				{

					e.getPlayer().sendMessage("§2[Info]:§f You can't place that here");
					e.setCancelled(true);

				}

			}

		}

	}

	// ---------------------------------------
	// A: Prevent disallowed players from stepping on pressure plates when locked
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void pStepOnBlockInRegion( PlayerBlockStepEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
		{

			if (!r.isAllowed(e.getPlayer()))
			{

				if (r.isLocked() && prot_step_lockables.contains(e.getBlock().getType()))
					e.setCancelled(true);

			}

		}

	}

	// ---------------------------------------
	// A: Apply thrower name to area of effect cloud for application checking
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void lingeringSplashListener( LingeringPotionSplashEvent e )
	{

		e.getAreaEffectCloud().setCustomName(e.getEntity().getUniqueId().toString());
		e.getAreaEffectCloud().setCustomNameVisible(false);

	}

	// ---------------------------------------
	// A: Prevent potion application if the type is malign and the thrower isn't allowed/pvp isn't enabled
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void areaEffectCloudInRegion( AreaEffectCloudApplyEvent e )
	{

		for (LivingEntity affected : e.getAffectedEntities())
		{

			Region r = MMO.region_manager.getRegion(affected.getLocation());

			if (r != null)
			{

				// Allow safe potion types to apply
				if (!safe_potions.contains(e.getEntity().getBasePotionData().getType().getEffectType()))
				{

					// Prevent application from disallowed to always protected
					if (prot_entities_always.contains(affected.getType())
							&& !r.isAllowed(UUID.fromString(e.getEntity().getCustomName())))
						e.setCancelled(true);
					// Prevent application if pvp is disabled and affected is a pvpoff protected type
					else if (!r.isPVP() && prot_entities_pvpoff.contains(affected.getType()))
						e.setCancelled(true);

				}

			}

		}

	}

	// ---------------------------------------
	// A:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void potionSplashListener( PotionSplashEvent e )
	{

		boolean is_malign = false;

		for (PotionEffect effect : e.getEntity().getEffects())
		{

			if (!safe_potions.contains(effect.getType()))
				is_malign = true;

		}

		if (is_malign)
		{

			for (LivingEntity affected : e.getAffectedEntities())
			{

				Region r = MMO.region_manager.getRegion(affected.getLocation());

				if (r != null)
				{

					Entity shooter = (Entity) e.getEntity().getShooter();

					// Protect these entities always unless the shooter is an allowed player
					if (prot_entities_always.contains(affected.getType()) && !r.isAllowed(shooter.getUniqueId()))
						e.setCancelled(true);
					// Protect these entities if pvp is off unless the shooter is an allowed player
					else if (!r.isPVP())
						// Players still take damage from non-players
						if (affected instanceof Player && !(shooter instanceof Player))
							return;
						else if (affected instanceof Player && shooter instanceof Player)
						{

							shooter.sendMessage("§2[Info]:§f Your magic won't work on that");
							e.setCancelled(true);

						}
						else if (prot_entities_pvpoff.contains(affected.getType())
								&& !r.isAllowed(shooter.getUniqueId()))
						{

							shooter.sendMessage("§2[Info]:§f Your magic won't work on that");
							e.setCancelled(true);

						}

				}

			}

		}

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void projectileHitListener( ProjectileHitEvent e )
	{

		// TODO Test and cancel damage events first, then follow up here for unhandled events
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
	public void vehicleDamageListener( VehicleDamageEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getVehicle().getLocation());

		// Explosions ruin the event
		if (e.getAttacker() != null)
		{

			if (r != null)
			{

				if (!r.isAllowed(e.getAttacker().getUniqueId()))
				{

					if (prot_entities_always.contains(e.getVehicle().getType()))
					{

						e.getAttacker().sendMessage("§2[Info]:§f You can't break that");
						e.setCancelled(true);

					}

					if (prot_entities_pvpoff.contains(e.getVehicle().getType()) && !r.isPVP())
					{

						e.getAttacker().sendMessage("§2[Info]:§f You can't break that");
						e.setCancelled(true);

					}

				}

			}

		}
		else
			e.setCancelled(true);

	}

	// ---------------------------------------
	// A:
	// B:
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void vehicleDamageListener( VehicleDestroyEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getVehicle().getLocation());

		// Explosions ruin the event
		if (e.getAttacker() != null)
		{

			if (r != null)
			{

				if (!r.isAllowed(e.getAttacker().getUniqueId()))
				{

					if (prot_entities_always.contains(e.getVehicle().getType()))
					{

						e.getAttacker().sendMessage("§2[Info]:§f You can't break that");
						e.setCancelled(true);

					}

					if (prot_entities_pvpoff.contains(e.getVehicle().getType()) && !r.isPVP())
					{

						e.getAttacker().sendMessage("§2[Info]:§f You can't break that");
						e.setCancelled(true);

					}

				}

			}

		}
		else
			e.setCancelled(true);

	}

	// ---------------------------------------
	// TODO: test
	// A: Prevent creation of nether portals from portal linking if the created portal is in a region
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
	// A: Prevent spawning of entities in regions unless it is from a specific spawn reason or monster/animal spawning
	// enabled
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eSpawnInRegion( EntitySpawnEvent e )
	{

		if (e instanceof CreatureSpawnEvent)
		{

			CreatureSpawnEvent ce = (CreatureSpawnEvent) e;
			if (ce.getSpawnReason() == SpawnReason.CUSTOM)
				return;
			if (ce.getSpawnReason() == SpawnReason.CURED)
				return;
			if (ce.getSpawnReason() == SpawnReason.SLIME_SPLIT)
				return;
			if (ce.getSpawnReason() == SpawnReason.SPAWNER_EGG)
				return;

		}

		Region r = MMO.region_manager.getRegion(e.getLocation());

		if (r != null)
			if (!r.isMonsterSpawning() && (e.getEntity() instanceof Monster || e.getEntity() instanceof Hoglin
					|| e.getEntity() instanceof Ghast || e.getEntity() instanceof Phantom
					|| e.getEntity() instanceof Shulker || e.getEntity() instanceof Slime))
				e.setCancelled(true);
			else if (!r.isAnimalSpawning() && e.getEntity() instanceof Animals)
				e.setCancelled(true);

	}

	// ---------------------------------------
	// A: Prevent disallowed from using cauldrons in regions
	// ---------------------------------------
	@EventHandler(ignoreCancelled = false)
	public void eUseCauldron( CauldronLevelChangeEvent e )
	{

		Region r = MMO.region_manager.getRegion(e.getBlock().getLocation());

		if (r != null)
			if (!r.isAllowed(e.getEntity().getUniqueId()))
			{

				e.getEntity().sendMessage("§2[Info]:§f You can't use that cauldron");
				e.setCancelled(true);

			}

	}

}
