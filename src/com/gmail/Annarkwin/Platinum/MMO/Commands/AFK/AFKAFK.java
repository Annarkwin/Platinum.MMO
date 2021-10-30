package com.gmail.Annarkwin.Platinum.MMO.Commands.AFK;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;

public class AFKAFK extends PlatinumCommand
{

	public AFKAFK( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
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

		return true;

	}

}
