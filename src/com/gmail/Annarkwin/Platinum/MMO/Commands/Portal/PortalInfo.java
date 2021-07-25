package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalInfo extends PlatinumCommand
{

	public PortalInfo( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Portal parg;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the portal");
			return true;

		}

		if ((parg = MMO.portal_manager.getPortal(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No portal with that name found");
			return true;

		}

		p.sendMessage("Portal Info");
		p.sendMessage(" Portal Name: " + parg.getName());
		p.sendMessage(" Warp Name: " + parg.getWarp().getName());
		p.sendMessage(" Enabled: " + parg.isEnabled());
		p.sendMessage(" Hook: " + parg.getHook());
		return true;

	}

}
