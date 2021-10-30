package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryRemove extends PlatinumCommand
{

	public QuarryRemove( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Quarry q = MMO.quarry_manager.getQuarry(p.getLocation());

		if (args.length > 1)
		{

			if (MMO.quarry_manager.getQuarry(args[1]) == null)
			{

				p.sendMessage("§4[Error]:§f No quarry with that name exists");
				return true;

			}

			MMO.quarry_manager.removeQuarry(args[1]);

		}
		else
		{

			if (q == null)
			{

				p.sendMessage("§4[Error]:§f No quarry found");
				return true;

			}

			MMO.quarry_manager.removeQuarry(q);

		}

		p.sendMessage("§2[Info]:§f Quarry removed");

		return true;

	}

}
