package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryRefill implements Subcommand
{

	private String description = "Refill a quarry";
	private MainCommand main;
	private String name = "refill";
	private String permission = "platinum.quarry.refill";
	private boolean playeronly = true;
	private String usage = "/quarry refill <quarry>";

	public QuarryRefill( MainCommand maincommand )
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
		Quarry qarg;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the quarry");
			return;

		}

		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return;

		}

		qarg.refill();
		p.sendMessage("§2[Info]:§f Quarry has been filled");

	}

}
