package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionPVP implements Subcommand
{

	private String description = "Toggle region PVP";
	private MainCommand main;
	private String name = "pvp";
	private String permission = "platinum.region.pvp";
	private boolean playeronly = true;
	private String usage = "/region pvp";

	public RegionPVP( MainCommand maincommand )
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
		else if (r.isOwner(p))
		{

			r.setPVP(!r.isPVP());
			p.sendMessage("§2[Info]:§f Region PVP has been set to " + r.isPVP());

		}
		else
		{

			p.sendMessage("§4[Error]:§f You aren't the owner of this region");

		}

	}

}
