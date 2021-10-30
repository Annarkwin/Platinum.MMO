package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpGo extends PlatinumCommand
{

	public WarpGo( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Warp warg;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the warp");
			return true;

		}

		if ((warg = MMO.warp_manager.getWarp(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No warp with that name found");
			return true;

		}

		if (!warg.getHook().equalsIgnoreCase("") && !p.hasPermission(warg.getHook()))
		{

			p.sendMessage("§4[Error]:§f You aren't allowed to that warp");
			return true;

		}

		if (!warg.isEnabled() && !p.hasPermission("platinum.warp.go.admin"))
		{

			p.sendMessage("§4[Error]:§f That warp is disabled");
			return true;

		}

		p.teleport(warg.getLocation());
		p.sendMessage("§2[Info]:§f You have warped");

		return true;

	}

}
