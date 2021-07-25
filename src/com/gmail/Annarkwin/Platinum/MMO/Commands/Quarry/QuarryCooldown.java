package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryCooldown extends PlatinumCommand
{

	public QuarryCooldown( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	public int getInt( String s )
	{

		return Integer.parseInt(s);

	}

	public boolean isInt( String s )
	{

		try
		{

			Integer.parseInt(s);
			return true;

		}
		catch (NumberFormatException e)
		{

			return false;

		}

	}
	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Quarry qarg;

		if (args.length < 3)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the quarry then the time in minutes");
			return true;

		}

		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return true;

		}

		if (!isInt(args[2]))
		{

			p.sendMessage("§4[Error]:§f Invalid time");
			return true;

		}

		qarg.setCooldownSeconds(getInt(args[2]));
		p.sendMessage("§2[Info]:§f Quarry timer set");
		return true;

	}

}
