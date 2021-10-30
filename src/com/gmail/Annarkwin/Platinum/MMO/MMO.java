package com.gmail.Annarkwin.Platinum.MMO;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.PlatinumMainCommand;
import com.gmail.Annarkwin.Platinum.API.TickerEvent;
import com.gmail.Annarkwin.Platinum.API.TickerEvent.TickerEventType;
import com.gmail.Annarkwin.Platinum.API.Events.RegisterAPIEvents;
import com.gmail.Annarkwin.Platinum.MMO.Commands.AFK.AFKAFK;
import com.gmail.Annarkwin.Platinum.MMO.Commands.AFK.AFKHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.AFK.CommandAFK;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest.CommandEnderchest;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest.EnderchestClear;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest.EnderchestDrop;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest.EnderchestHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest.EnderchestOpen;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory.CommandInventory;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory.InventoryClear;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory.InventoryCopy;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory.InventoryDrop;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory.InventoryHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory.InventoryOpen;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.CommandPortal;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalDisable;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalEnable;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalHook;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalInfo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalList;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalNew;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalRemove;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalSetWarp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Portal.PortalUnhook;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.CommandQuarry;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryCooldown;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryDisable;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryEnable;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryGo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryHook;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryInfo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryList;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryNew;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryRefill;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryRemove;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarrySetBlock;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarrySetTP;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry.QuarryUnhook;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.CommandRegion;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionAllow;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionAllowed;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionAnimals;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionGive;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionGrow;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionInfo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionKick;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionLock;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionMonsters;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionNew;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionPVP;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionPublic;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionRemove;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Region.RegionSetWarp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Spawn.CommandSpawn;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Spawn.SpawnHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Sudo.CommandSudo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Sudo.SudoHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Sudo.SudoRun;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Test.CommandTest;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.CommandWarp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpDisable;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpEnable;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpGo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpHelp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpHook;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpInfo;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpList;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpNew;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpRemove;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpSetWarp;
import com.gmail.Annarkwin.Platinum.MMO.Commands.Warp.WarpUnhook;
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

		PlatinumMainCommand afk = new CommandAFK("afk", "platinum.command.afk", true, "Set your afk status",
				"/afk help (page)");
		afk.addChildCommand(new AFKAFK("afk", "platinum.command.afk", true, "Toggle afk", "/afk afk"));
		afk.addChildCommand(new AFKHelp("help", "platinum.command.afk.help", true, "Get afk help", "/afk help (page)"));
		getCommand("AFK").setExecutor(afk);

		PlatinumMainCommand enderchest = new CommandEnderchest("enderchest", "platinum.command.enderchest", true,
				"Control enderchests", "/enderchest help (page)");
		enderchest.addChildCommand(new EnderchestClear("clear", "platinum.command.enderchest.clear", true,
				"Clear enderchest contents", "/enderchest clear (player)"));
		enderchest.addChildCommand(new EnderchestDrop("drop", "platinum.command.enderchest.drop", true,
				"Drop enderchest contents", "/enderchest drop (player)"));
		enderchest.addChildCommand(new EnderchestHelp("help", "platinum.command.enderchest.help", true,
				"Show enderchest help", "/enderchest help (page)"));
		enderchest.addChildCommand(new EnderchestOpen("open", "platinum.command.enderchest.open", true,
				"Open an enderchest", "/enderchest open (player)"));
		getCommand("Enderchest").setExecutor(enderchest);

		PlatinumMainCommand inventory = new CommandInventory("inventory", "platinum.command.inventory", true,
				"Control inventories", "/inventory help (page)");
		inventory.addChildCommand(new InventoryClear("clear", "platinum.command.inventory.clear", true,
				"Clear inventory contents", "/inventory clear (player)"));
		inventory.addChildCommand(new InventoryCopy("copy", "platinum.command.inventory.copy", true,
				"Copy inventory contents", "/inventory copy <player>"));
		inventory.addChildCommand(new InventoryDrop("drop", "platinum.command.inventory.drop", true,
				"Drop inventory contents", "/inventory drop (player)"));
		inventory.addChildCommand(new InventoryHelp("help", "platinum.command.inventory.help", true,
				"Show inventory help", "/inventory help (page)"));
		inventory.addChildCommand(new InventoryOpen("open", "platinum.command.inventory.open", true,
				"Open an inventory", "/inventory open (player)"));
		getCommand("Inventory").setExecutor(inventory);

		PlatinumMainCommand portal = new CommandPortal("portal", "platinum.command.portal", true, "Control portals",
				"/portal help (page)");
		portal.addChildCommand(new PortalDisable("disable", "platinum.command.portal.disable", true, "Disable a portal",
				"/portal disable <portal>"));
		portal.addChildCommand(new PortalEnable("enable", "platinum.command.portal.enable", true, "Enable a portal",
				"/portal enable <portal>"));
		portal.addChildCommand(new PortalHelp("help", "platinum.command.portal.help", true, "Show portal help",
				"/portal help (page)"));
		portal.addChildCommand(new PortalHook("hook", "platinum.command.portal.hook", true, "Set portal permission",
				"/portal hook <portal> <hook>"));
		portal.addChildCommand(new PortalInfo("info", "platinum.command.portal.info", true, "Get info of a portal",
				"/portal info <portal>"));
		portal.addChildCommand(
				new PortalList("list", "platinum.command.portal.list", true, "List portals", "/portal list"));
		portal.addChildCommand(new PortalNew("new", "platinum.command.portal.new", true, "Create a portal",
				"/portal new <name> <warp>"));
		portal.addChildCommand(new PortalRemove("remove", "platinum.command.portal.remove", true, "Remove a portal",
				"/portal remove <portal>"));
		portal.addChildCommand(new PortalSetWarp("setwarp", "platinum.command.portal.setwarp", true,
				"Change a portal warp", "/portal setwarp <portal> <warp>"));
		portal.addChildCommand(new PortalUnhook("unhook", "platinum.command.portal.unhook", true,
				"Remove portal permission", "/portal unhook <portal>"));
		getCommand("Portal").setExecutor(portal);

		PlatinumMainCommand quarry = new CommandQuarry("quarry", "platinum.command.quarry", true, "Control quarries",
				"/quarry help (page)");
		quarry.addChildCommand(new QuarryCooldown("cooldown", "platinum.command.quarry.cooldown", true,
				"Set quarry refill time", "/quarry cooldown <quarry> <seconds>"));
		quarry.addChildCommand(new QuarryDisable("disable", "platinum.command.quarry.disable", true, "Disable a quarry",
				"/quarry disable <quarry>"));
		quarry.addChildCommand(new QuarryEnable("enable", "platinum.command.quarry.enable", true, "Enable a quarry",
				"/quarry enable <quarry>"));
		quarry.addChildCommand(
				new QuarryGo("go", "platinum.command.quarry.go", true, "Teleport to a quarry", "/quarry go <quarry>"));
		quarry.addChildCommand(new QuarryHelp("help", "platinum.command.quarry.help", true, "Show quarry help",
				"/quarry help (page)"));
		quarry.addChildCommand(new QuarryHook("hook", "platinum.command.quarry.hook", true, "Set quarry permission",
				"/quarry hook <quarry> <hook>"));
		quarry.addChildCommand(new QuarryInfo("info", "platinum.command.quarry.info", true, "Get info of a quarry",
				"/quarry info <quarry>"));
		quarry.addChildCommand(
				new QuarryList("list", "platinum.command.quarry.list", true, "List quarries", "/quarry list"));
		quarry.addChildCommand(
				new QuarryNew("new", "platinum.command.quarry.new", true, "Create a quarry", "/quarry new <name>"));
		quarry.addChildCommand(new QuarryRefill("refill", "platinum.command.quarry.refill", true, "Refill a quarry",
				"/quarry refill <quarry>"));
		quarry.addChildCommand(new QuarryRemove("remove", "platinum.command.quarry.remove", true, "Remove a quarry",
				"/quarry remove <quarry>"));
		quarry.addChildCommand(new QuarrySetBlock("setblock", "platinum.command.quarry.setblock", true,
				"Set quarry block", "/quarry setblock <quarry>"));
		quarry.addChildCommand(new QuarrySetTP("settp", "platinum.command.quarry.settp", true, "Set quarry tp",
				"/quarry settp <quarry>"));
		quarry.addChildCommand(new QuarryUnhook("unhook", "platinum.command.quarry.unhook", true,
				"Remove quarry permission", "/quarry unhook <quarry>"));
		getCommand("Quarry").setExecutor(quarry);

		PlatinumMainCommand region = new CommandRegion("region", "platinum.command.region", true, "Control regions",
				"/region help (page)");
		region.addChildCommand(new RegionAllow("allow", "platinum.command.region.allow", true, "Allow player to region",
				"/region allow <player>"));
		region.addChildCommand(new RegionAllowed("allowed", "platinum.command.region.allowed", true,
				"List allowed players", "/region allowed"));
		region.addChildCommand(new RegionAnimals("animals", "platinum.command.region.animals", true,
				"Toggle animal spawning", "/region animals"));
		region.addChildCommand(new RegionGive("give", "platinum.command.region.give", true, "Give region to player",
				"/region give <player>"));
		region.addChildCommand(
				new RegionGrow("grow", "platinum.command.region.grow", true, "Toggle block growth", "/region grow"));
		region.addChildCommand(new RegionHelp("help", "platinum.command.region.help", true, "Show region help",
				"/region help (page)"));
		region.addChildCommand(
				new RegionInfo("info", "platinum.command.region.info", true, "Get info of a region", "/region info"));
		region.addChildCommand(new RegionKick("kick", "platinum.command.region.kick", true, "Kick player from region",
				"/region kick <player>"));
		region.addChildCommand(
				new RegionLock("lock", "platinum.command.region.lock", true, "Lock region devices", "/region lock"));
		region.addChildCommand(new RegionMonsters("monsters", "platinum.command.region.monsters", true,
				"Toggle monster spawning", "/region monsters"));
		region.addChildCommand(
				new RegionNew("new", "platinum.command.region.new", true, "Create a region", "/region new"));
		region.addChildCommand(new RegionPublic("public", "platinum.command.region.public", true,
				"Toggle public breaking", "/region public"));
		region.addChildCommand(new RegionPVP("pvp", "platinum.command.region.pvp", true, "Toggle pvp", "/region pvp"));
		region.addChildCommand(new RegionRemove("remove", "platinum.command.region.remove", true, "Remove a region",
				"/region remove"));
		region.addChildCommand(new RegionSetWarp("setwarp", "platinum.command.region.setwarp", true, "Set region tp",
				"/region settp"));
		getCommand("Region").setExecutor(region);

		PlatinumMainCommand spawn = new CommandSpawn("spawn", "platinum.command.spawn", true,
				"TP to the spawn warp point", "/spawn help (page)");
		spawn.addChildCommand(
				new SpawnHelp("help", "platinum.command.spawn.help", true, "Show spawn help", "/spawn help (page)"));
		getCommand("Spawn").setExecutor(spawn);

		PlatinumMainCommand sudo = new CommandSudo("sudo", "platinum.command.sudo", true,
				"Force others to run commands", "/sudo help (page)");
		sudo.addChildCommand(
				new SudoHelp("help", "platinum.command.sudo.help", true, "Show sudo help", "/sudo help (page)"));
		sudo.addChildCommand(new SudoRun("run", "platinum.command.sudo.run", true, "Force others to run commands",
				"/sudo run <player> <command string>"));
		getCommand("Sudo").setExecutor(sudo);

		getCommand("Test").setExecutor(new CommandTest());

		PlatinumMainCommand warp = new CommandWarp("warp", "platinum.command.warp", true, "Warp to locations",
				"/warp help (page)");
		warp.addChildCommand(new WarpDisable("disable", "platinum.command.warp.disable", true, "Disable a warp",
				"/warp disable <warp>"));
		warp.addChildCommand(
				new WarpEnable("enable", "platinum.command.warp.enable", true, "Enable a warp", "/warp enable <warp>"));
		warp.addChildCommand(
				new WarpGo("go", "platinum.command.warp.go", true, "Warp to locations", "/warp go <warp>"));
		warp.addChildCommand(
				new WarpHelp("help", "platinum.command.warp.help", true, "Show warp help", "/warp help (page)"));
		warp.addChildCommand(new WarpHook("hook", "platinum.command.warp.hook", true, "Set warp permission",
				"/warp hook <warp> <hook>"));
		warp.addChildCommand(
				new WarpInfo("info", "platinum.command.warp.info", true, "Get warp info", "/warp info <warp>"));
		warp.addChildCommand(new WarpList("list", "platinum.command.warp.list", true, "List warps", "/warp list"));
		warp.addChildCommand(
				new WarpNew("new", "platinum.command.warp.new", true, "Create a warp", "/warp new <name>"));
		warp.addChildCommand(
				new WarpRemove("remove", "platinum.command.warp.remove", true, "Remove a warp", "/warp remove <warp>"));
		warp.addChildCommand(new WarpSetWarp("setwarp", "platinum.command.warp.setwarp", true, "Set a warp location",
				"/warp setwarp <warp>"));
		warp.addChildCommand(new WarpUnhook("unhook", "platinum.command.warp.unhook", true, "Remove warp permission",
				"/warp unhook <warp>"));
		getCommand("Warp").setExecutor(warp);

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
