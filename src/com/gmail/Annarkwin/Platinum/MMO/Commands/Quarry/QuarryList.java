package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryList implements Subcommand
{

	private String description = "List quarries";
	private MainCommand main;
	private String name = "list";
	private String permission = "platinum.quarry.list";
	private boolean playeronly = true;
	private String usage = "/quarry list";

	public QuarryList( MainCommand maincommand )
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

	}

}
