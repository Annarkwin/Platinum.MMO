package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionKick extends PlatinumCommand
{

	public RegionKick( String name, String permission, boolean player, String description, String usage )
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

		return true;

	}

}
