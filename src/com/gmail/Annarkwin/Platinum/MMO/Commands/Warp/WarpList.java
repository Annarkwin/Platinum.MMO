package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpList implements Subcommand
{

	private String description = "List warps";
	private MainCommand main;
	private String name = "list";
	private String permission = "platinum.warp.list";
	private boolean playeronly = true;
	private String usage = "/warp list";

	public WarpList( MainCommand maincommand )
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
		p.sendMessage("§2[Info]:§f Allowed warps:");

		for (Warp wrp : MMO.warp_manager.getWarps())
		{

			if (wrp.getHook().equalsIgnoreCase("") || p.hasPermission(wrp.getHook()))
			{

				message = " - " + wrp.getName();

				if (!wrp.isEnabled())
					message = "§4" + message;

				p.sendMessage(message);

			}

		}

		p.sendMessage("---");

	}

}
