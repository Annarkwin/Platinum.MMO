package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpNew extends PlatinumCommand
{

	public WarpNew( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;

		if (!(args.length > 1))
		{

			p.sendMessage("§4[Error]:§f Enter a name");
			return true;

		}

		if (MMO.warp_manager.getWarp(args[1]) != null)
		{

			p.sendMessage("§4[Error]:§f That name is in use");
			return true;

		}

		MMO.warp_manager.addWarp(new Warp(args[1], p.getLocation()));
		p.sendMessage("§2[Info]:§f Warp created");
		return true;

	}

}
