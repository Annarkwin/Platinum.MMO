package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpDisable extends PlatinumCommand
{

	public WarpDisable( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Warp parg;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the warp");
			return true;

		}

		if ((parg = MMO.warp_manager.getWarp(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No warp with that name found");
			return true;

		}

		if (!parg.isEnabled())
		{

			p.sendMessage("§4[Error]:§f That warp is already disabled");
			return true;

		}

		p.sendMessage("§2[Info]:§f Warp usability has been set to " + parg.toggleEnabled());
		return true;

	}

}
