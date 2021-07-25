package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.CommandHelper;
import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionAllowed extends PlatinumCommand
{

	public RegionAllowed( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
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
		return true;

	}

}
