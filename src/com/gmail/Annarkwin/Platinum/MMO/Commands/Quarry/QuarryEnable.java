package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryEnable implements Subcommand
{

	private String description = "Enable a quarry";
	private MainCommand main;
	private String name = "enable";
	private String permission = "platinum.quarry.enable";
	private boolean playeronly = true;
	private String usage = "/quarry enable <quarry>";

	public QuarryEnable( MainCommand maincommand )
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

			p.sendMessage("�4[Error]:�f Enter the name of the quarry");
			return;

		}

		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("�4[Error]:�f No quarry with that name found");
			return;

		}

		if (qarg.isEnabled())
		{

			p.sendMessage("�4[Error]:�f That quarry is already enabled");
			return;

		}

		p.sendMessage("�2[Info]:�f Quarry usability has been set to " + qarg.toggleEnabled());

	}

}
