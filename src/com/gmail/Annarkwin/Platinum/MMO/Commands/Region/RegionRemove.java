package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionRemove implements Subcommand
{

	private String description = "Remove a region";
	private MainCommand main;
	private String name = "remove";
	private String permission = "platinum.region.remove";
	private boolean playeronly = true;
	private String usage = "/region remove";

	public RegionRemove( MainCommand maincommand )
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

			p.sendMessage("�4[Error]:�f You are not standing in a region");

		}
		else if (r.isOwner(p))
		{

			MMO.region_manager.removeRegion(r);
			p.sendMessage("�2[Info]: �4You have removed the region you were standing in!");

		}
		else
		{

			p.sendMessage("�4[Error]:�f You aren't the owner of this region");

		}

	}

}
