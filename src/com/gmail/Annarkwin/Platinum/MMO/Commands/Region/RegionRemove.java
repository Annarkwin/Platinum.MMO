package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionRemove extends PlatinumCommand
{

	public RegionRemove( String name, String permission, boolean player, String description, String usage )
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
		return true;

	}

}
