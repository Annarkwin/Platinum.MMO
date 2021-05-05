package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryHook implements Subcommand
{

	private String description = "Set permission hook";
	private MainCommand main;
	private String name = "hook";
	private String permission = "platinum.quarry.hook";
	private boolean playeronly = true;
	private String usage = "/quarry hook <quarry> <perm>";

	public QuarryHook( MainCommand maincommand )
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

		if (args.length < 3)
		{

			p.sendMessage("§4[Error]:§f Enter the hook of the quarry");
			return;

		}

		qarg.setHook(args[2]);
		p.sendMessage("§2[Info]:§f Quarry hooked");

	}

}
