package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionGive implements Subcommand
{

	private String description = "Give a region to a player";
	private MainCommand main;
	private String name = "give";
	private String permission = "platinum.region.give";
	private boolean playeronly = true;
	private String usage = "/region give <player>";

	public RegionGive( MainCommand maincommand )
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

			// /Region Allow arg2
			if (args.length > 1)
			{

				Player target = Bukkit.getPlayer(args[1]);

				if (target == null)
				{

					p.sendMessage("§4[Error]:§f That isn't a valid online player");

				}
				else if (r.getOwner().equals(target.getUniqueId()))
				{

					p.sendMessage("§4[Error]:§f That player already owned this region");

				}
				else
				{

					p.sendMessage("§2[Info]:§f Giving region to " + target.getName());
					r.removeAllowed(p.getUniqueId());
					r.removeAllowed(target.getUniqueId());
					r.setOwner(target.getUniqueId());

				}

			}
			else
			{

				p.sendMessage("§4[Error]:§f You must enter an online player to allow");

			}

		}
		else
		{

			p.sendMessage("§4[Error]:§f You aren't the owner of this region");

		}

	}

}
