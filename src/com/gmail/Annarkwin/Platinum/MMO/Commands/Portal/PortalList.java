package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalList extends PlatinumCommand
{

	public PortalList( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		String message = "";

		p.sendMessage("§2[Info]:§f Allowed portals:");

		for (Portal ptl : MMO.portal_manager.getPortals())
		{

			if (ptl.getHook().equalsIgnoreCase("") || p.hasPermission(ptl.getHook()))
			{

				message = " - " + ptl.getName();

				if (!ptl.isEnabled())
					message = "§4" + message;

				p.sendMessage(message);

			}

		}

		p.sendMessage("---");
		return true;

	}

}
