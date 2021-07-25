package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarrySetTP extends PlatinumCommand
{

	public QuarrySetTP( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Quarry qa;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the quarry");
			return true;

		}

		if ((qa = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return true;

		}

		if (MMO.quarry_manager.getQuarry(p.getLocation()) != null)
		{

			p.sendMessage("§4[Error]:§f Stand outside of quarries");
			return true;

		}

		qa.setWarpLocation(p.getLocation());
		p.sendMessage("§2[Info]:§f Quarry warp location set");
		return true;

	}

}
