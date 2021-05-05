package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionKick implements Subcommand
{

	private String description = "Kick a player from a region";
	private MainCommand main;
	private String name = "kick";
	private String permission = "platinum.region.kick";
	private boolean playeronly = true;
	private String usage = "/region kick";

	public RegionKick( MainCommand maincommand )
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

			if (args.length > 1)
			{

				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

				if (target == null)
				{

					p.sendMessage("§4[Error]:§f That isn't a valid player");

				}
				else if (target == p)
				{

					p.sendMessage("§4[Error]:§f You can't kick yourself");

				}
				else if (r.isAllowed(target.getUniqueId()))
				{

					p.sendMessage("§2[Info]:§f Removing " + target.getName() + " from region's allow list");
					r.removeAllowed(target.getUniqueId());

				}
				else
				{

					p.sendMessage("§4[Error]:§f That player is isn't allowed");

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
