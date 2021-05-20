package com.gmail.Annarkwin.Platinum.MMO;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.TickerEvent;
import com.gmail.Annarkwin.Platinum.API.Events.RegisterAPIEvents;
import com.gmail.Annarkwin.Platinum.API.TickerEvent.TickerEventType;
import com.gmail.Annarkwin.Platinum.MMO.Commands.AFK.CommandAFK;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest.CommandEnderchest;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory.CommandInventory;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.CommandPortal;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.CommandQuarry;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.CommandRegion;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Spawn.CommandSpawn;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Sudo.CommandSudo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Test.CommandTest;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.CommandWarp;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.PortalManager;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.QuarryManager;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.RegionManager;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.UserManager;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.WarpManager;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerChat;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerCrops;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerGameChanger;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerMOTD;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerPortals;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerQuarries;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerRegionProtection;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerScoreboardUpdate;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerSelection;
import com.gmail.Annarkwin.Platinum.MMO.Listeners.ListenerUser;

public class MMO extends JavaPlugin
{

	public static UserManager user_manager;
	public static QuarryManager quarry_manager;
	public static PortalManager portal_manager;
	public static WarpManager warp_manager;
	public static RegionManager region_manager;

	@Override
	public void onEnable()
	{

		// Load configuration serializable classes
		registerSerializables();

		// Retrieve file data
		user_manager = new UserManager(this, "Users.yml");
		quarry_manager = new QuarryManager(this, "Quarries.yml");
		portal_manager = new PortalManager(this, "Portals.yml");
		region_manager = new RegionManager(this, "Regions.yml");
		warp_manager = new WarpManager(this, "Warps.yml");

		// Enable plugin features
		enableListeners();
		enableCommands();

		// Initialize update event
		TickerEvent.startTicker(this, 100L, TickerEventType.QUARRY_FILL);
		TickerEvent.startTicker(this, 4L, TickerEventType.PORTAL);
		TickerEvent.startTicker(this, 20L, TickerEventType.SCOREBOARD_UPDATE);

	}

	@Override
	public void onDisable()
	{

		user_manager.saveData();
		quarry_manager.saveData();
		portal_manager.saveData();
		region_manager.saveData();
		warp_manager.saveData();

	}

	private void registerSerializables()
	{

		// TODO Move this to the classes themselves
		ConfigurationSerialization.registerClass(User.class, "User");
		ConfigurationSerialization.registerClass(Quarry.class, "Quarry");
		ConfigurationSerialization.registerClass(Portal.class, "Portal");
		ConfigurationSerialization.registerClass(Warp.class, "Warp");
		ConfigurationSerialization.registerClass(Region.class, "Zone");

	}

	public void enableCommands()
	{

		getCommand("AFK").setExecutor(new CommandAFK());
		getCommand("Enderchest").setExecutor(new CommandEnderchest());
		getCommand("Inventory").setExecutor(new CommandInventory());
		getCommand("Portal").setExecutor(new CommandPortal());
		getCommand("Quarry").setExecutor(new CommandQuarry());
		getCommand("Region").setExecutor(new CommandRegion());
		getCommand("Spawn").setExecutor(new CommandSpawn());
		getCommand("Sudo").setExecutor(new CommandSudo());
		getCommand("Test").setExecutor(new CommandTest());
		getCommand("Warp").setExecutor(new CommandWarp());

	}

	public void enableListeners()
	{

		getServer().getPluginManager().registerEvents(new RegisterAPIEvents(), this);
		getServer().getPluginManager().registerEvents(new ListenerChat(), this);
		getServer().getPluginManager().registerEvents(new ListenerCrops(), this);
		getServer().getPluginManager().registerEvents(new ListenerGameChanger(), this);
		getServer().getPluginManager().registerEvents(new ListenerMOTD(), this);
		getServer().getPluginManager().registerEvents(new ListenerPortals(), this);
		getServer().getPluginManager().registerEvents(new ListenerQuarries(), this);
		getServer().getPluginManager().registerEvents(new ListenerRegionProtection(), this);
		getServer().getPluginManager().registerEvents(new ListenerScoreboardUpdate(), this);
		getServer().getPluginManager().registerEvents(new ListenerSelection(Material.STICK), this);
		getServer().getPluginManager().registerEvents(new ListenerUser(), this);

	}

}
