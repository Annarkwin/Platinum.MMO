package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalRemove extends PlatinumCommand
{

	public PortalRemove( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Portal port = MMO.portal_manager.getPortal(p.getLocation());
		Portal parg;

		if (args.length > 1)
		{

			if ((parg = MMO.portal_manager.getPortal(args[1])) == null)
			{

				p.sendMessage("§4[Error]:§f No portal with that name exists");
				return true;

			}

			MMO.portal_manager.removePortal(parg);

		}
		else
		{

			if (port == null)
			{

				p.sendMessage("§4[Error]:§f No portal found");
				return true;

			}

			MMO.portal_manager.removePortal(port);

		}

		p.sendMessage("§2[Info]:§f Portal removed");
		return true;

	}

}
