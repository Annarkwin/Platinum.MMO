package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryGo extends PlatinumCommand
{

	public QuarryGo( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Quarry qarg;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the quarry");
			return true;

		}

		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return true;

		}

		if (!qarg.getHook().equalsIgnoreCase("") && !p.hasPermission(qarg.getHook()))
		{

			p.sendMessage("§4[Error]:§f You aren't allowed to that quarry");
			return true;

		}

		p.teleport(qarg.getWarpLocation());
		p.sendMessage("§2[Info]:§f You have been warped");
		return true;

	}

}
