package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryList extends PlatinumCommand
{

	public QuarryList( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		String message = "";
		p.sendMessage("§2[Info]:§f Allowed quarries:");

		for (Quarry quar : MMO.quarry_manager.getQuarries())
		{

			if (quar.getHook().equalsIgnoreCase("") || p.hasPermission(quar.getHook()))
			{

				message = " - " + quar.getName();

				if (!quar.isEnabled())
					message = "§4" + message;

				p.sendMessage(message);

			}

		}

		p.sendMessage("---");
		return true;

	}

}
