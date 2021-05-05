package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionInfo implements Subcommand
{

	private String description = "Show region info";
	private MainCommand main;
	private String name = "info";
	private String permission = "platinum.region.info";
	private boolean playeronly = true;
	private String usage = "/region info";

	public RegionInfo( MainCommand maincommand )
	{

		main = maincommand;

	}

	@Override
	public String getDescription()
	{

		return description;

	}

	@Override
	public MainCommand getMainCommand()
	{

		return main;

	}

	@Override
	public String getName()
	{

		return name;

	}

	@Override
	public String getPermission()
	{

		return permission;

	}

	@Override
	public String getUsage()
	{

		return usage;

	}

	@Override
	public boolean isPlayerOnly()
	{

		return playeronly;

	}

	@Override
	public void run( CommandSender sender, String[] args )
	{

		Player p = (Player) sender;
		Region r = MMO.region_manager.getRegion(p.getLocation());

		if (r == null)
		{

			p.sendMessage("§4[Error]:§f You are not standing in a region");

		}
		else
		{

			p.sendMessage("§2[Region Info]: §f" + r.getID());
			p.sendMessage(" §2Name: §f" + r.getName());
			p.sendMessage(" §2Owner: §f" + Bukkit.getOfflinePlayer(r.getOwner()).getName());
			p.sendMessage(" §2Animals: §f" + r.isAnimalSpawning());
			p.sendMessage(" §2Monsters: §f" + r.isMonsterSpawning());
			p.sendMessage(" §2Locked: §f" + r.isLocked());
			p.sendMessage(" §2Public: §f" + r.isPublic());
			p.sendMessage(" §2PVP: §f" + r.isPVP());

		}

	}

}
