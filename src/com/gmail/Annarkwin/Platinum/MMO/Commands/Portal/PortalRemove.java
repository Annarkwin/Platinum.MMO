package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalRemove implements Subcommand
{

	private String description = "Remove a portal";
	private MainCommand main;
	private String name = "remove";
	private String permission = "platinum.portal.remove";
	private boolean playeronly = true;
	private String usage = "/portal remove";

	public PortalRemove( MainCommand maincommand )
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
		Portal port = MMO.portal_manager.getPortal(p.getLocation());
		Portal parg;

		if (args.length > 1)
		{

			if ((parg = MMO.portal_manager.getPortal(args[1])) == null)
			{

				p.sendMessage("§4[Error]:§f No portal with that name exists");
				return;

			}

			MMO.portal_manager.removePortal(parg);

		}
		else
		{

			if (port == null)
			{

				p.sendMessage("§4[Error]:§f No portal found");
				return;

			}

			MMO.portal_manager.removePortal(port);

		}

		p.sendMessage("§2[Info]:§f Portal removed");

	}

}
