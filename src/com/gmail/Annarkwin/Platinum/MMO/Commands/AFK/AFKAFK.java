package com.gmail.Annarkwin.Platinum.MMO.Commands.AFK;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class AFKAFK implements Subcommand
{

	private String description = "Set your status to AFK";
	private MainCommand main;
	private String name = "AFK";
	private String permission = "platinum.afk.afk";
	private boolean playeronly = true;
	private String usage = "/afk afk";

	public AFKAFK( MainCommand maincommand )
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

		Player p = ((Player) sender);

		if (p.getPlayerListName().contains("§4-AFK- §7"))
		{

			p.sendMessage("§4[Error]:§f You are already AFK");

		}
		else
		{

			p.setPlayerListName("§4-AFK- §7" + p.getPlayerListName());
			p.setSleepingIgnored(true);
			p.sendMessage("§2[Info]:§f You are now AFK");

		}

	}

}
