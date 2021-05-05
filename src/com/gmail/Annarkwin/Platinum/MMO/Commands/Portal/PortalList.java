package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalList implements Subcommand
{

	private String description = "List portals";
	private MainCommand main;
	private String name = "list";
	private String permission = "platinum.portal.list";
	private boolean playeronly = true;
	private String usage = "/portal list";

	public PortalList( MainCommand maincommand )
	{

		main = maincommand;

	}

	@Override
	public String getDescription()
	{

		return description;

	}

	@Override
	public MainCommand getMainCommand()
	{

		return main;

	}

	@Override
	public String getName()
	{

		return name;

	}

	@Override
	public String getPermission()
	{

		return permission;

	}

	@Override
	public String getUsage()
	{

		return usage;

	}

	@Override
	public boolean isPlayerOnly()
	{

		return playeronly;

	}

	@Override
	public void run( CommandSender sender, String[] args )
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

	}

}
