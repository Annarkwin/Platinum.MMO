package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.CommandHelper;
import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionAllowed implements Subcommand
{

	private String description = "List allowed players in current zone";
	private MainCommand main;
	private String name = "allowed";
	private String permission = "platinum.region.allowed";
	private boolean playeronly = true;
	private String usage = "/region allowed";

	public RegionAllowed( MainCommand maincommand )
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

			String[] entries = r.getAllowedNames().toArray(new String[0]);

			if (args.length > 1)
			{

				if (CommandHelper.isPositiveInt(args[1]))
				{

					CommandHelper.sendHelp(sender, entries, "Region Allow List", CommandHelper.getInt(args[1]));

				}
				else
				{

					sender.sendMessage("§4[Error]:§f Enter a positive number");

				}

			}
			else
			{

				CommandHelper.sendHelp(sender, entries, "Region Allow List", 1);

			}

		}

	}

}
